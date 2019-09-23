package com.wzlue.order.service;

import com.wzlue.order.entity.OrderEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:17
 */
public interface OrderService {
	
	OrderEntity queryObject(Long id);
	
	List<OrderEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderEntity order);
	
	void update(OrderEntity order);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void create(OrderEntity order);

	Map<String, Object> statistics(Long memberId);

	void sendGoods(String orderNumber, Long companyId, String companyName, String logisticsNumber);

	void payNotify(OrderEntity order);

	void payNotifyMember(OrderEntity order);

	OrderEntity queryByOrderNumber(String orderNumber);

	Map<String, Object> statistics();

	List<Map<String, Object>> queryOrderChart();
}
