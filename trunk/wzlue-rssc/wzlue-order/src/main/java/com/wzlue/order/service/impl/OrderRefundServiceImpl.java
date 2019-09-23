package com.wzlue.order.service.impl;

import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.wzlue.common.utils.NumberUtils;
import com.wzlue.contact.dao.RefundLogisticsDao;
import com.wzlue.contact.entity.RefundLogisticsEntity;
import com.wzlue.member.dao.MemberInfoDao;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.order.dao.*;
import com.wzlue.order.entity.OrderEntity;
import com.wzlue.order.entity.OrderLogisticsEntity;
import com.wzlue.order.entity.OrderRefundEntity;
import com.wzlue.order.entity.OrderRefundPicEntity;
import com.wzlue.order.service.OrderRefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("orderRefundService")
public class OrderRefundServiceImpl implements OrderRefundService {
	@Autowired
	private OrderRefundDao orderRefundDao;
	@Autowired
	private OrderRefundPicDao orderRefundPicDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private WxPayService wxService;
	@Autowired
	private OrderLogisticsDao orderLogisticsDao;
	@Autowired
	private OrderGoodsDao orderGoodsDao;
	@Autowired
	private MemberInfoDao memberInfoDao;
	@Autowired
	public ApiIntegralScordSaveTwo apiIntegralScordSave;
	@Autowired
	public RefundLogisticsDao refundLogisticsDao;

	@Override
	public OrderRefundEntity queryObject(Long id) {
		OrderRefundEntity orderRefund = orderRefundDao.queryObject(id);
		if (orderRefund.getStatus() > 1) {
			OrderLogisticsEntity orderLogistics = orderLogisticsDao.queryByOrderNumber(orderRefund.getRefundNumber());
			if (orderLogistics != null) {
				orderRefund.setOrderLogistics(orderLogistics);
			}
		}
		return orderRefund;
	}

	@Override
	public List<OrderRefundEntity> queryList(Map<String, Object> map) {
		return orderRefundDao.queryList(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return orderRefundDao.queryTotal(map);
	}

	@Override
	@Transactional
	/**
	 * 退款申请
	 */
	public void save(OrderRefundEntity orderRefund) {
		String refundNumber = NumberUtils.getOrderNumder();
		orderRefund.setRefundNumber(refundNumber);
		orderRefundDao.save(orderRefund);
		String[] picUrls = orderRefund.getPicUrls();
		for (String picUrl : picUrls) {
			OrderRefundPicEntity orderRefundPic = new OrderRefundPicEntity();
			orderRefundPic.setRefundNumber(refundNumber);
			orderRefundPic.setPicUrl(picUrl);
			orderRefundPicDao.save(orderRefundPic);
		}
	/*	OrderEntity order = new OrderEntity();
		order.setOrderNumber(orderRefund.getOrderNumber());
		order.setStatus(4);//4退款中
		orderDao.updateByOrderNumber(order);*/
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(OrderRefundEntity orderRefund) throws WxPayException {
		orderRefund.setProcessTime(new Date());
		orderRefundDao.update(orderRefund);//退款退货---状态扭转（5退款成功  6退款失败）
		orderRefund = orderRefundDao.queryObject(orderRefund.getId());//退款信息详情
		OrderEntity order = orderDao.queryByOrderNumber(orderRefund.getOrderNumber());	//对应的订单信息
//		OrderGoodsEntity orderGoods = orderGoodsDao.queryObject(orderRefund.getOrderGoodId());//对应的商品订单信息
		MemberInfoEntity memberInfo = memberInfoDao.queryObject(order.getMemberId());	//个人信息
		if (orderRefund.getStatus() == 5) { //同意
			if (orderRefund.getType() == 1) {//类型 1：退款
				WxPayRefundRequest request = new WxPayRefundRequest();
				request.setOutTradeNo(orderRefund.getOrderNumber());//商户订单号
				request.setOutRefundNo(orderRefund.getRefundNumber());//商户退款单号
				request.setAppid(wxService.getConfig().getAppId());//小程序appid
				request.setMchId(wxService.getConfig().getMchId());//商户id
				String nonceStr = String.valueOf(System.currentTimeMillis());
				request.setNonceStr(nonceStr);//随机字符串
				request.setRefundDesc("退款");//退款原因
				int buyNum = orderGoodsDao.buyNumOrder(orderRefund.getOrderNumber());//订单总共商品件数

				Map<String, Object> params = new HashMap<>();
				params.put("orderNumber",orderRefund.getOrderNumber());
				params.put("status",5);
				//此订单的退款数量总和
				int reNum = orderRefundDao.reNumOrder(params);
				//实退积分
				int reIn;
				int rIn=0;
				if(buyNum==reNum){//如果全退款
					params.put("refundId",orderRefund.getId());//当前退款id
					int count=orderRefundDao.queryTotal(params);
					if(count>1){
						//已经退的积分
						rIn = orderRefundDao.reIntegral(params);
					}
					int orderIntegral=order.getUseIntegral();//所用积分
					reIn=orderIntegral-rIn;//所剩余积分
					orderRefund.setRefundIntegral(reIn);//退款的积分
					order.setStatus(0);
					orderDao.update(order);
				}else{
					BigDecimal priceIn=orderRefund.getRefundAmount();//等到订单所花费积分
					reIn=priceIn.intValue();//取整
					orderRefund.setRefundIntegral(reIn);//退款的积分
				}

				if (order.getPayType().equals("wechat")){//微信:wechat  积分:integral，混合:mix
					request.setTotalFee(orderRefund.getRefundAmount().multiply(new BigDecimal(100)).intValue());//订单金额转换分
					request.setRefundFee(orderRefund.getRefundAmount().multiply(new BigDecimal(100)).intValue());//退款金额
					wxService.refund(request);
					orderRefundDao.update(orderRefund);
				} else if (order.getPayType().equals("integral")){//除订单最后一条均退整数位积分

					memberInfo.setIntegral(memberInfo.getIntegral()+reIn);
					memberInfoDao.update(memberInfo);

					orderRefundDao.update(orderRefund);//保存此次退款的积分
					//退款退积分加积分记录
					apiIntegralScordSave.insertIntegralRecord(order.getMemberId(),"退款退回积分",reIn,7,orderRefund.getId(),orderRefund.getOrderNumber(),0);

				} else if (order.getPayType().equals("mix")){
					//只买1件商品(积分退积分 微信退微信)
					if(buyNum==1){
                      Integer integral=order.getUseIntegral();//付款的积分
						if(order.getWechatAmount().compareTo(BigDecimal.ZERO)!=0){//不等于0的时候执行微信退款
							request.setTotalFee(order.getWechatAmount().multiply(new BigDecimal(100)).intValue());//订单金额转换分
							request.setRefundFee(order.getWechatAmount().multiply(new BigDecimal(100)).intValue());//退款金额
							wxService.refund(request);
						}
						memberInfo.setIntegral(memberInfo.getIntegral()+integral);
						memberInfoDao.update(memberInfo);
						//退款退积分 加积分记录
						apiIntegralScordSave.insertIntegralRecord(order.getMemberId(),"退款退回积分",integral,7,orderRefund.getId(),orderRefund.getOrderNumber(),0);
   						orderRefundDao.update(orderRefund);
					}else{
						//多件混合支付退款（全退积分）
						memberInfo.setIntegral(memberInfo.getIntegral()+reIn);
						memberInfoDao.update(memberInfo);

						orderRefundDao.update(orderRefund);//保存此次退款的积分
						//退款退积分加积分记录
						apiIntegralScordSave.insertIntegralRecord(order.getMemberId(),"退款退回积分",reIn,7,orderRefund.getId(),orderRefund.getOrderNumber(),0);

					}

				}


			} else {//类型2：退款退货
				//退货
				orderRefund.setStatus(2);//待取件(1待处理  2待取件  3已取件  4已收件  5退款成功  6退款失败)
				orderRefundDao.update(orderRefund);
			}
		} else if (orderRefund.getStatus() == 6) {//不同意

		}

	}

	public static void main(String[] args) {
		BigDecimal priceIn = new BigDecimal("12.9");
		int pin  = priceIn.intValue(); // 取整数位
		System.out.println(pin);
	}

	@Override
	public void delete(Long id) {
		orderRefundDao.delete(id);
	}

	@Override
	public void deleteBatch(Long[] ids) {
		orderRefundDao.deleteBatch(ids);
	}

	@Override
	public OrderRefundEntity queryByOrderNumber(String orderNumber) {
		return orderRefundDao.queryByOrderNumber(orderNumber);
	}

	/**
	 * 买家退货（退货订单变为已取件，添加一条退货物流信息）
	 *
	 * @param refundNumber
	 * @param companyId
	 * @param companyName
	 * @param logisticsNumber
	 */
	@Override
	public void sendGoods(String refundNumber, Long companyId, String companyName, String logisticsNumber,Long logisticsId,String logisticsPrice) {
		OrderRefundEntity orderRefund = orderRefundDao.queryByRefundNumber(refundNumber);
		orderRefund.setStatus(3);//已取件(1待处理  2待取件  3已取件  4已收件  5退款成功  6退款失败)
		orderRefundDao.update(orderRefund);
		RefundLogisticsEntity refundLogisticsEntity = new RefundLogisticsEntity();//-----退货物流
		refundLogisticsEntity.setLogisticsId(companyId);//物流公司id
		refundLogisticsEntity.setLogisticsName(companyName);//物流名称
		refundLogisticsEntity.setLogisticsNumber(logisticsNumber);//物流单号
		refundLogisticsEntity.setOrderRefund(refundNumber);//退款编号
		BigDecimal b = new BigDecimal(logisticsPrice);
		refundLogisticsEntity.setLogisticsPrice(b);
		//添加一条物流信息
		if(logisticsId!=null){//存在的话修改退货物流
			refundLogisticsEntity.setId(logisticsId);
			refundLogisticsDao.update(refundLogisticsEntity);

		}else{//添加退货物流
			refundLogisticsDao.save(refundLogisticsEntity);
		}

	}

	/**
	 * 确认收货后退款
	 *
	 * @param orderRefund
	 * @throws WxPayException
	 */
	@Override
	public void refundReceipt(OrderRefundEntity orderRefund) throws WxPayException {
		orderRefund.setProcessTime(new Date());
		orderRefundDao.update(orderRefund);//4已收件
		orderRefund = orderRefundDao.queryObject(orderRefund.getId());
		OrderEntity order = new OrderEntity();
		//订单状态--订单的状态 0已取消 1待付款  2 待发货  3待收货  4退款中 5退款成功 6退款失败 7已完成
		order.setOrderNumber(orderRefund.getOrderNumber());
		//微信退款
		WxPayRefundRequest request = new WxPayRefundRequest();
		request.setOutTradeNo(orderRefund.getOrderNumber());//商户订单号
		request.setOutRefundNo(orderRefund.getRefundNumber());//商户退款单号
		request.setAppid(wxService.getConfig().getAppId());//appid
		request.setMchId(wxService.getConfig().getMchId());//商户id
		request.setRefundDesc("退款");//退款原因
		OrderEntity orderEntity = orderDao.queryByOrderNumber(orderRefund.getOrderNumber());
		request.setTotalFee(orderEntity.getPaymentAmount().multiply(new BigDecimal(100)).intValue());//订单金额
		request.setRefundFee(orderRefund.getRefundAmount().multiply(new BigDecimal(100)).intValue()+orderRefund.getExpressFee().multiply(new BigDecimal(100)).intValue());//退款金额+快递费
		wxService.refund(request);
		//订单状态---5退款成功
		order.setStatus(5);
		//修改订单状态
		orderDao.updateByOrderNumber(order);
		//退款订单5退款成功
		orderRefund.setStatus(5);
		orderRefundDao.update(orderRefund);
	}

}
