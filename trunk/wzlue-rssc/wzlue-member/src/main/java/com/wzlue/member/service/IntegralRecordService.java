package com.wzlue.member.service;

import com.wzlue.member.entity.IntegralRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 积分记录
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 11:19:34
 */
public interface IntegralRecordService {
	
	IntegralRecordEntity queryObject(Long id);
	
	List<IntegralRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IntegralRecordEntity integralRecord);
	
	void update(IntegralRecordEntity integralRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
