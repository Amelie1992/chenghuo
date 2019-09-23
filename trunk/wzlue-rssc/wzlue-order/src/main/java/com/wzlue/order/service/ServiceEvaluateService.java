package com.wzlue.order.service;


import com.wzlue.order.entity.ServiceEvaluateEntity;

import java.util.List;
import java.util.Map;

/**
 * 售后服务评价
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-09 10:28:07
 */
public interface ServiceEvaluateService {
	
	ServiceEvaluateEntity queryObject(Long id);
	
	List<ServiceEvaluateEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ServiceEvaluateEntity serviceEvaluate);
	
	void update(ServiceEvaluateEntity serviceEvaluate);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
