package com.wzlue.menu.service;

import com.wzlue.menu.entity.CustomMenuEntity;

import java.util.List;
import java.util.Map;

/**
 * 自定义菜单
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 14:17:34
 */
public interface CustomMenuService {
	
	CustomMenuEntity queryObject(Long id);
	
	List<CustomMenuEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(CustomMenuEntity customMenu);
	
	void update(CustomMenuEntity customMenu);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    void show(Long id);
}
