package com.wzlue.goods.service;

import com.wzlue.goods.entity.BrandEntity;

import java.util.List;
import java.util.Map;

/**
 * 品牌
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 14:29:56
 */
public interface BrandService {
	
	BrandEntity queryObject(Long id);
	
	List<BrandEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(BrandEntity brand);
	
	void update(BrandEntity brand);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
