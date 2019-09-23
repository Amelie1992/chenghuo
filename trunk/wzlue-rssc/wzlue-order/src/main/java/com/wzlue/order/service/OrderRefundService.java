package com.wzlue.order.service;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.wzlue.order.entity.OrderRefundEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 退款
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-08 21:03:13
 */
public interface OrderRefundService {
	
	OrderRefundEntity queryObject(Long id);
	
	List<OrderRefundEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderRefundEntity orderRefund);
	
	void update(OrderRefundEntity orderRefund) throws WxPayException;
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	OrderRefundEntity queryByOrderNumber(String orderNumber);

	/**
	 * 退货发货
	 * @param refundNumber
	 * @param companyName
	 * @param logisticsNumber
	 */
	void sendGoods(String refundNumber, Long companyId, String companyName, String logisticsNumber, Long logisticsId, String logisticsPrice);

	void refundReceipt(OrderRefundEntity orderRefund) throws WxPayException;
}
