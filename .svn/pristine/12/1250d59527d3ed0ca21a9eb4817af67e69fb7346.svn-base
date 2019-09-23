package com.wzlue.order.service.impl;

import java.util.*;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.wzlue.member.dao.MemberRecommendDao;
import com.wzlue.member.entity.MemberRecommendEntity;
import com.wzlue.order.dao.*;
import com.wzlue.order.entity.*;
import com.wzlue.sys.dao.SysConfigDao;
import com.wzlue.sys.entity.SysConfigEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wzlue.common.utils.NumberUtils;
import com.wzlue.goods.dao.GoodsDao;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.member.dao.IntegralRecordDao;
import com.wzlue.member.dao.MemberInfoDao;
import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.order.service.OrderService;


@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderGoodsDao orderGoodsDao;
    @Autowired
    private OrderLogisticsDao orderLogisticsDao;
    @Autowired
    private OrderAddressDao orderAddressDao;
    @Autowired
    private OrderInvoiceDao orderInvoiceDao;
    @Autowired
    private IntegralRecordDao integralRecordDao;
    @Autowired
    private MemberInfoDao memberInfoDao;
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private SysConfigDao sysConfigDao;
    @Autowired
    private MemberRecommendDao mMemberRecommendDao;
    @Autowired
    private IntegralRecordSave integralRecordSave;
    @Autowired
    private OrderRefundDao orderRefundDao;


    @Override
    public OrderEntity queryObject(Long id) {
        OrderEntity order = orderDao.queryObject(id);
        if (order.getStatus() == 3 || order.getStatus() == 4 || order.getStatus() == 5 || order.getStatus() == 6 || order.getStatus() == 7) {
            OrderLogisticsEntity orderLogistics = orderLogisticsDao.queryByOrderNumber(order.getOrderNumber());
            order.setOrderLogistics(orderLogistics);
        }
        return order;
    }

    @Override
    public List<OrderEntity> queryList(Map<String, Object> map) {
        return orderDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDao.queryTotal(map);
    }

    @Override
    public void save(OrderEntity order) {
        orderDao.save(order);
    }

    @Override
    public void update(OrderEntity order) {
        orderDao.update(order);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        orderDao.deleteBatch(ids);
    }

    @Transactional
    @Override
    public void create(OrderEntity order) {
        String orderNumber = NumberUtils.getOrderNumder();
        order.setOrderNumber(orderNumber);
        order.setOrderType(order.getOrderType());//订单类型1实物2虚拟
        orderDao.save(order);
        if (order.getOrderGoodsList() != null) {
            List<OrderGoodsEntity> orderGoodsList = order.getOrderGoodsList();
            for (OrderGoodsEntity orderGoods : orderGoodsList) {
                orderGoods.setOrderNumber(orderNumber);
                orderGoodsDao.save(orderGoods);
            }

        }
        if (order.getOrderAddress() != null) {
            OrderAddressEntity orderAddress = order.getOrderAddress();
            orderAddress.setOrderNumber(orderNumber);
            orderAddressDao.save(orderAddress);
        }

        if (order.getOrderInvoice() != null) {
            OrderInvoiceEntity orderInvoice = order.getOrderInvoice();
            orderInvoice.setOrderNumber(orderNumber);
            orderInvoiceDao.save(order.getOrderInvoice());
        }
    }

    @Override
    public Map<String, Object> statistics(Long memberId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("memberId", memberId);
        map.put("orderType", 22);//正常订单
        map.put("status", 1); //待付款
        Integer pendingPayment = orderDao.queryTotal(map);

        map.put("status", 2); //待发货
        Integer pendingDelivery = orderDao.queryTotal(map);

        map.put("status", 3); //待发货
        Integer pendingReceive = orderDao.queryTotal(map);

        Map<String, Object> mapTwo = new HashMap<String, Object>();
        mapTwo.put("memberId", memberId);
        int refund = orderRefundDao.queryTotal(mapTwo);
//		map.put("status", 4); //退款中
//		Integer refundOne = orderDao.queryTotal(map);
//		map.put("status", 5); //退款成功
//		Integer refundTwo = orderDao.queryTotal(map);
//		map.put("status", 6); //退款失败
//		Integer refundThree = orderDao.queryTotal(map);
//		Integer refund=refundOne+refundTwo+refundThree;

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pendingPayment", pendingPayment);
        result.put("pendingDelivery", pendingDelivery);
        result.put("pendingReceive", pendingReceive);
        result.put("refund", refund);
        return result;
    }

    @Override
    public void sendGoods(String orderNumber, Long companyId, String companyName, String logisticsNumber) {
        OrderEntity order = new OrderEntity();
        order.setOrderNumber(orderNumber);
        order.setStatus(3);//----3待收货
        order.setDeliveryTime(new Date());//发货时间
        GregorianCalendar cal = new GregorianCalendar();
        cal.add(GregorianCalendar.DATE, 10);
        order.setConfirmTime(cal.getTime());//系统确认收货时间=发货时间+10
        orderDao.updateByOrderNumber(order);//--根据订单号修改状态
        OrderLogisticsEntity orderLogistics = new OrderLogisticsEntity();//-----订单物流
        orderLogistics.setFlag(1);//发货订单物流
        orderLogistics.setCompanyId(companyId);
        orderLogistics.setCompanyName(companyName);
        orderLogistics.setOrderNumber(orderNumber);
        orderLogistics.setLogisticsNumber(logisticsNumber);
        orderLogisticsDao.save(orderLogistics);
    }

    /**
     * 订单支付成功
     *
     * @param order
     */
    @Override
    @Transactional
    public void payNotify(OrderEntity order) {
        OrderEntity orderEntity = orderDao.queryByOrderNumber(order.getOrderNumber());//订单
        MemberInfoEntity memberInfo = memberInfoDao.queryObject(orderEntity.getMemberId());//用户信息
        if (orderEntity.getOrderType().equals("2") && orderEntity.getPayType().equals("mix")) {//虚拟订单混合
            Integer useIntegral = orderEntity.getUseIntegral();//得到的积分
            Integer yuan = useIntegral;
            Integer integral = memberInfo.getIntegral();//正常积分
            Integer freezingIntegral = memberInfo.getFreezingIntegral();//冻结积分
            Integer leftIntegral = 0;
            useIntegral = useIntegral - freezingIntegral;
            if (useIntegral >= 0) {
                memberInfo.setFreezingIntegral(0);
                //积分记录保存
                integralRecordSave.insertIntegralRecord(memberInfo.getId(), "耗费冻结积分", freezingIntegral, 4, orderEntity.getId(), orderEntity.getOrderNumber(), 1);
            } else {
                leftIntegral = freezingIntegral - yuan;
                memberInfo.setFreezingIntegral(0);
                integralRecordSave.insertIntegralRecord(memberInfo.getId(), "耗费冻结积分", freezingIntegral, 4, orderEntity.getId(), orderEntity.getOrderNumber(), 1);
            }
            if (leftIntegral > 0) {
                integral = integral + leftIntegral;
                memberInfo.setIntegral(integral);//剩余积分
                integralRecordSave.insertIntegralRecord(memberInfo.getId(), "解冻积分", leftIntegral, 5, orderEntity.getId(), orderEntity.getOrderNumber(), 0);
            } else {
                useIntegral = useIntegral - integral;//减去正常积分
                if (useIntegral > 0) {
                    memberInfo.setIntegral(useIntegral);
                } else {
                    memberInfo.setIntegral(0);
                }
                integralRecordSave.insertIntegralRecord(memberInfo.getId(), "耗费正常积分", integral, 5, orderEntity.getId(), orderEntity.getOrderNumber(), 0);

            }

            memberInfo.setIsVip(1);
            memberInfo.setMobile(orderEntity.getRamarks());//手机号绑定
            memberInfo.setVipAddTime(new Date());
            Calendar curr = Calendar.getInstance();
            curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);//会员有效期一年
            memberInfo.setVipEndTime(curr.getTime());
            memberInfoDao.update(memberInfo);
            insertExtendedIntegral(memberInfo.getId(), memberInfo.getId(), memberInfo.getId(), memberInfo.getNickName());//返积分

        } else if (orderEntity.getOrderType().equals("2") && orderEntity.getPayType().equals("wechat")) {//微信支付
            memberInfo.setMobile(orderEntity.getRamarks());//手机号绑定
            memberInfo.setIsVip(1);
            memberInfo.setVipAddTime(new Date());
            Calendar curr = Calendar.getInstance();
            curr.set(Calendar.YEAR, curr.get(Calendar.YEAR) + 1);//会员有效期一年
            memberInfo.setVipEndTime(curr.getTime());
            memberInfoDao.update(memberInfo);
            insertExtendedIntegral(memberInfo.getId(), memberInfo.getId(), memberInfo.getId(), memberInfo.getNickName());//返积分
        } else {
            orderDao.updateByOrderNumber(order);
            //订单商品销量加1
            List<OrderGoodsEntity> orderGoodsList = orderEntity.getOrderGoodsList();
            for (OrderGoodsEntity orderGoodsEntity : orderGoodsList) {
                goodsDao.addSaleNum(orderGoodsEntity.getGoodsId());
            }

        }
        insertExtendedIntegral(memberInfo.getId(), memberInfo.getId(), memberInfo.getId(), memberInfo.getNickName());//返积分


        //积分使用记录
//		IntegralRecordEntity integralRecord = new IntegralRecordEntity();
//		integralRecord.setCreateTime(new Date());
//		integralRecord.setIntegral(orderEntity.getPaymentAmount().intValue() * 100);
//		integralRecord.setMemberId(orderEntity.getMemberId());
//		integralRecord.setRemarks("消费获得");
//		integralRecordDao.save(integralRecord);
//
//		if(orderEntity.getUseIntegral() != null && orderEntity.getUseIntegral() > 0){
//			IntegralRecordEntity ir = new IntegralRecordEntity();
//			ir.setCreateTime(new Date());
//			ir.setIntegral(orderEntity.getUseIntegral());
//			ir.setMemberId(orderEntity.getMemberId());
//			integralRecord.setRemarks("消费使用");
//
//			MemberInfoEntity memberInfo = new MemberInfoEntity();
//			memberInfo.setId(orderEntity.getMemberId());
//			memberInfo.setIntegral(memberInfo.getIntegral() - orderEntity.getUseIntegral());
//			memberInfoDao.update(memberInfo);
//		}


    }

    /**
     * 虚拟订单支付成功
     *
     * @param order
     */
    @Override
    @Transactional
    public void payNotifyMember(OrderEntity order) {
        OrderEntity orderEntity = orderDao.queryByOrderNumber(order.getOrderNumber());
        order.setStatus(7);
        order.setPayTime(new Date());
        orderDao.updateByOrderNumber(order);
        JsonParser jp = new JsonParser();
        //推广返的积分
        SysConfigEntity sysConfigEntity = sysConfigDao.queryByKey("INTEGRAL_SETTING");
        String value2 = sysConfigEntity.getValue();
        JsonObject jo2 = jp.parse(value2).getAsJsonObject();
        int extendedIntegral = Integer.valueOf(jo2.get("popularization").getAsJsonObject().get("get").getAsString());
        MemberInfoEntity memberInfo = memberInfoDao.queryObject(order.getMemberId());

        //推广返积分
        if (memberInfo.getRecommenderId() != 0) {
            //推荐人积分加
            MemberInfoEntity reMemberInfo = memberInfoDao.queryObject(memberInfo.getRecommenderId());
            int score2 = reMemberInfo.getIntegral() + extendedIntegral;
            reMemberInfo.setIntegral(score2);
            //推广加积分记录
            integralRecordSave.insertIntegralRecord(reMemberInfo.getId(), "推广返积分", extendedIntegral, 1, memberInfo.getId(), memberInfo.getNickName(), 0);

            memberInfoDao.update(reMemberInfo);


        }
    }


    @Override
    public OrderEntity queryByOrderNumber(String orderNumber) {
        return orderDao.queryByOrderNumber(orderNumber);
    }

    @Override
    public Map<String, Object> statistics() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", 1); //待付款
        Integer pendingPayment = orderDao.queryTotal(map);

        map.put("status", 2); //待发货
        Integer pendingDelivery = orderDao.queryTotal(map);

        map.put("status", 3); //待发货
        Integer pendingReceive = orderDao.queryTotal(map);

        map.put("status", 4); //退款
        Integer refund = orderDao.queryTotal(map);

        Integer orderCount = orderDao.queryOrderCount(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pendingPayment", pendingPayment);
        result.put("pendingDelivery", pendingDelivery);
        result.put("pendingReceive", pendingReceive);
        result.put("refund", refund);
        result.put("orderCount", orderCount);
        return result;
    }

    @Override
    public List<Map<String, Object>> queryOrderChart() {
        return orderDao.queryOrderChart();
    }

    /**
     * 添加推广积分
     *
     * @param memberId 登录用户id
     * @param userId   当前用户id
     * @param tuiId    返积分的用户id
     * @param nickName 返积分的用户昵称
     */
    @Transactional
    public void insertExtendedIntegral(Long memberId, Long userId, Long tuiId, String nickName) {
        JsonParser jp = new JsonParser();
        SysConfigEntity sysConfigEntity = sysConfigDao.queryByKey("INTEGRAL_SETTING");//推广成功返的积分的多少
        String value2 = sysConfigEntity.getValue();
        JsonObject jo2 = jp.parse(value2).getAsJsonObject();
        int extendedIntegral = Integer.valueOf(jo2.get("popularization").getAsJsonObject().get("get").getAsString());//返的积分值
        MemberInfoEntity memberInfoEntity = memberInfoDao.queryObject(memberId);//获取用户详情

        if (memberInfoEntity.getRecommenderId() == 0) {//如果用户没有推荐人

        } else {
            MemberInfoEntity RecommenderInfoEntity = memberInfoDao.queryObject(memberInfoEntity.getRecommenderId());//获取推荐人详情
            Long daId = RecommenderInfoEntity.getId();
            Long xiaoId =memberInfoEntity.getId();
            int count = integralRecordDao.queryCount(daId,xiaoId);
            if(count<1){//没有给过推广的积分
                RecommenderInfoEntity.setIntegral(RecommenderInfoEntity.getIntegral() + extendedIntegral);//正常积分中加入推广返的积分
                memberInfoDao.update(RecommenderInfoEntity);
                integralRecordSave.insertIntegralRecord(RecommenderInfoEntity.getId(), "推广返积分", extendedIntegral, 1, memberInfoEntity.getId(), memberInfoEntity.getNickName(), 0);


            }

        }
    }

}
