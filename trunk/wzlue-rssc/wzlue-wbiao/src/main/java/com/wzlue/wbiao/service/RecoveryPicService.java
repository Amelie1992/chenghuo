package com.wzlue.wbiao.service;

import com.wzlue.wbiao.entity.RecoveryPicEntity;

import java.util.List;
import java.util.Map;

/**
 * 回收置换图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
public interface RecoveryPicService {
	
	RecoveryPicEntity queryObject(Long id);
	
	List<RecoveryPicEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(RecoveryPicEntity recoveryPic);
	
	void update(RecoveryPicEntity recoveryPic);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
