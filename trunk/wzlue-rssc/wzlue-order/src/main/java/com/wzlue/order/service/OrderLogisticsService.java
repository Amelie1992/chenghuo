package com.wzlue.order.service;

import com.wzlue.order.entity.OrderLogisticsEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单物流
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
public interface OrderLogisticsService {
	
	OrderLogisticsEntity queryObject(Long id);
	
	List<OrderLogisticsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderLogisticsEntity orderLogistics);
	
	void update(OrderLogisticsEntity orderLogistics);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
