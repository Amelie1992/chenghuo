package com.wzlue.order.service;

import com.wzlue.order.entity.LogisticsEntity;

import java.util.List;
import java.util.Map;

/**
 * 物流公司
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 10:25:16
 */
public interface LogisticsService {
	
	LogisticsEntity queryObject(Long id);
	
	List<LogisticsEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(LogisticsEntity logistics);
	
	void update(LogisticsEntity logistics);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
