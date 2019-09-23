package com.wzlue.wbiao.service;

import com.wzlue.wbiao.entity.ConditionEntity;

import java.util.List;
import java.util.Map;

/**
 * 成色
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
public interface ConditionService {
	
	ConditionEntity queryObject(Long id);
	
	List<ConditionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ConditionEntity condition);
	
	void update(ConditionEntity condition);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
