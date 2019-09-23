package com.wzlue.advert.service;

import com.wzlue.advert.entity.AdvertEntity;

import java.util.List;
import java.util.Map;

/**
 * 广告
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 15:18:40
 */
public interface AdvertService {
	
	AdvertEntity queryObject(Long id);
	
	List<AdvertEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(AdvertEntity advert);
	
	void update(AdvertEntity advert);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	//通过位置获取广告位
	List<AdvertEntity> mobileAll(Integer positionId);
}
