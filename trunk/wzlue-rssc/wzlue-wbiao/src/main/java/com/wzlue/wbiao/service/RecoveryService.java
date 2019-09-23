package com.wzlue.wbiao.service;

import com.wzlue.wbiao.entity.RecoveryEntity;

import java.util.List;
import java.util.Map;

/**
 * 回收置换
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
public interface RecoveryService {
	
	RecoveryEntity queryObject(Long id);
	
	List<RecoveryEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecoveryEntity recovery);
	
	void update(RecoveryEntity recovery);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
