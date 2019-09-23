package com.wzlue.order.service;

import com.wzlue.order.entity.OrderGoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单商品
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
public interface OrderGoodsService {
	
	OrderGoodsEntity queryObject(Long id);
	
	List<OrderGoodsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderGoodsEntity orderGoods);
	
	void update(OrderGoodsEntity orderGoods);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
