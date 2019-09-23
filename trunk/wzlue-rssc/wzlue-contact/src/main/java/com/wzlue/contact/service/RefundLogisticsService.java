package com.wzlue.contact.service;

import com.wzlue.contact.entity.RefundLogisticsEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-08-15 18:30:15
 */
public interface RefundLogisticsService {
	
	RefundLogisticsEntity queryObject(Long id);
	
	List<RefundLogisticsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RefundLogisticsEntity refundLogistics);
	
	void update(RefundLogisticsEntity refundLogistics);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
