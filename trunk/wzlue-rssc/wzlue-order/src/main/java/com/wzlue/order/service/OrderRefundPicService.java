package com.wzlue.order.service;

import com.wzlue.order.entity.OrderRefundPicEntity;

import java.util.List;
import java.util.Map;

/**
 * 退款图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-08 21:23:38
 */
public interface OrderRefundPicService {
	
	OrderRefundPicEntity queryObject(Integer id);
	
	List<OrderRefundPicEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(OrderRefundPicEntity orderRefundPic);
	
	void update(OrderRefundPicEntity orderRefundPic);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
