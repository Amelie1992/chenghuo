package com.wzlue.advert.service;

import com.wzlue.advert.entity.ExtensionPosterEntity;

import java.util.List;
import java.util.Map;

/**
 * 商户推广海报表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:18:34
 */
public interface ExtensionPosterService {
	
	ExtensionPosterEntity queryObject(Long id);
	
	List<ExtensionPosterEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ExtensionPosterEntity extensionPoster);
	
	void update(ExtensionPosterEntity extensionPoster);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	List<ExtensionPosterEntity> queryListApi(Map<String, Object> map);
}
