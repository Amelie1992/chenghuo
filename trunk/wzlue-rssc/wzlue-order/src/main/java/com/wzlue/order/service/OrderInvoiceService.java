package com.wzlue.order.service;

import com.wzlue.order.entity.OrderInvoiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单发票
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-15 10:36:04
 */
public interface OrderInvoiceService {
	
	OrderInvoiceEntity queryObject(Integer id);
	
	List<OrderInvoiceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderInvoiceEntity orderInvoice);
	
	void update(OrderInvoiceEntity orderInvoice);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
