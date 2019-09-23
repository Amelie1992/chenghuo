package com.wzlue.order.service;

import com.wzlue.order.entity.EvaluateEntity;

import java.util.List;
import java.util.Map;

/**
 * 订单评价
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-22 16:04:16
 */
public interface EvaluateService {
	
	EvaluateEntity queryObject(Long id);
	
	List<EvaluateEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(EvaluateEntity evaluate);
	
	void update(EvaluateEntity evaluate);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
