package com.wzlue.goods.service;

import com.wzlue.goods.entity.TagEntity;

import java.util.List;
import java.util.Map;

/**
 * 标签
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
public interface TagService {
	
	TagEntity queryObject(Long id);
	
	List<TagEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(TagEntity tag);
	
	void update(TagEntity tag);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
