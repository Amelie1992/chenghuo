package com.wzlue.integral.service;

import com.wzlue.integral.entity.IntegralCardBatchEntity;

import java.util.List;
import java.util.Map;

/**
 * 积分充值卡批次
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:48:04
 */
public interface IntegralCardBatchService {
	
	IntegralCardBatchEntity queryObject(Long id);
	
	List<IntegralCardBatchEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IntegralCardBatchEntity integralCardBatch);
	
	void update(IntegralCardBatchEntity integralCardBatch);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
