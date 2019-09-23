package com.wzlue.order.service;

import com.wzlue.order.entity.OrderAddressEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单收货地址
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:17
 */
public interface OrderAddressService {
	
	OrderAddressEntity queryObject(Long id);
	
	List<OrderAddressEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderAddressEntity orderAddress);
	
	void update(OrderAddressEntity orderAddress);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
