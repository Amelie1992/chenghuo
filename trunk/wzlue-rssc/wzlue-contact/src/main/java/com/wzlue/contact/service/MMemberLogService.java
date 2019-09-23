package com.wzlue.contact.service;

import com.wzlue.contact.entity.MMemberLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 每日访问表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-08-13 17:39:33
 */
public interface MMemberLogService {
	
	MMemberLogEntity queryObject(Integer id);
	
	List<MMemberLogEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MMemberLogEntity mMemberLog);
	
	void update(MMemberLogEntity mMemberLog);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
