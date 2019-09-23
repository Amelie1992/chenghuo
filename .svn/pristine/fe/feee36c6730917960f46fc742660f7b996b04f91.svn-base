package com.wzlue.app.controller.wechat;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.wzlue.order.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.wzlue.app.common.annotation.Login;
import com.wzlue.common.base.R;
import com.wzlue.common.utils.IPUtils;
import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.member.service.MemberInfoService;
import com.wzlue.order.entity.OrderEntity;
import com.wzlue.order.service.OrderService;

@RestController
@RequestMapping("/app/wechat/pay")
public class ApiPayController {
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private WxPayService wxPayService;
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDao orderDao;
	

	/**
     * 统一下单(详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1)
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    @Login
    @PostMapping("unifiedOrder")
    public R unifiedOrder(@RequestAttribute("userId") Long userId, @RequestBody OrderEntity order, HttpServletRequest req) throws WxPayException {
    	MemberInfoEntity userInfo = memberInfoService.queryObject(userId);
		OrderEntity orderEntityTwo=orderDao.queryTime(order.getOrderNumber());
		if(orderEntityTwo.getStatus()==1 || orderEntityTwo.getStatus()==2){
			SimpleDateFormat a = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = null;
			try {
				date = a.parse(a.format(orderEntityTwo.getCreateTime()));//订单创建时间

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			Calendar ca=Calendar.getInstance();
			ca.setTime(date);
			ca.add(Calendar.HOUR_OF_DAY, 24);


			Date date1=new Date();//系统当前时间
		    if(date1.before(ca.getTime())){//当前支付时间小于订单过期时间
				WxPayUnifiedOrderRequest request = new WxPayUnifiedOrderRequest();
				request.setOutTradeNo(order.getOrderNumber());//订单编号
				request.setOpenid(userInfo.getOpenid());//小程序ID
				request.setBody("称货商城");
				request.setNotifyURL(wxPayService.getConfig().getNotifyUrl());//通知地址
				System.out.println(wxPayService.getConfig().getNotifyUrl());
				request.setTotalFee(order.getPaymentAmount().multiply(new BigDecimal(100)).intValue());
				request.setSpbillCreateIp(IPUtils.getIpAddr(req));
				request.setTradeType("JSAPI");

				request.setTimeStart(a.format(date));//交易起始时间


				request.setTimeExpire(a.format(ca.getTime()));//交易结束时间
				WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(request);

				String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
				String nonceStr = String.valueOf(System.currentTimeMillis());

				//signKey 商户平台设置的密钥key
				//签名字段：appId，timeStamp，nonceStr，package，signType

				Map<String, String> params = new HashMap<String, String>();
				params.put("appId", wxPayService.getConfig().getAppId());
				params.put("timeStamp", timeStamp);
				params.put("nonceStr", nonceStr);
				params.put("package", "prepay_id=" + result.getPrepayId());
				params.put("signType", "MD5");

				String sign = SignUtils.createSign(params, wxPayService.getConfig().getMchKey(), "MD5");

				params.put("paySign", sign);

				return R.ok().put("data", params);
			}else{
		    	return R.ok().put("code","订单已过期");
			}


		}
		return  R.ok().put("code","订单已支付");
    }
    
    /**
     * 此方法需要改造，根据实际需要返回com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse对象
     */
    @PostMapping("/parseOrderNotifyResult")
    public String parseOrderNotifyResult(@RequestBody String xmlData) throws Exception {
		WxPayOrderNotifyResult wxPayOrderNotifyResult = wxPayService.parseOrderNotifyResult(xmlData);
		String orderNumber=wxPayOrderNotifyResult.getOutTradeNo();//商品订单号
		SimpleDateFormat a = new SimpleDateFormat("yyyyMMddHHmmss");
		String timeEnd=wxPayOrderNotifyResult.getTimeEnd();//支付完成时间
		Date date =a.parse(timeEnd) ;//订单创建时间
		SimpleDateFormat a1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date payTime=a1.parse(a1.format(date));

		String transactionId=wxPayOrderNotifyResult.getTransactionId();//微信支付订单号
		OrderEntity orderEntity=orderDao.queryOrder(orderNumber);//订单详情
		//回掉成功
    	if("SUCCESS".equals(wxPayOrderNotifyResult.getReturnCode())){
           if(orderEntity.getStatus()==1){//待付款的时候
               orderEntity.setStatus(2);//改为待发货
			   orderEntity.setTransactionId(transactionId);
			   orderEntity.setPayTime(payTime);
			   orderEntity.setTimeEnd(timeEnd);
			   orderDao.update(orderEntity);

		   }else if(orderEntity.getStatus()==0){//已取消状态

		   }else{
			   orderEntity.setTransactionId(transactionId);
			   orderEntity.setPayTime(payTime);
			   orderEntity.setTimeEnd(timeEnd);
			   orderDao.update(orderEntity);
		   }
    		return WxPayNotifyResponse.success("OK");
    	}else {
    		return WxPayNotifyResponse.success("OK");
    	}
    }
}
