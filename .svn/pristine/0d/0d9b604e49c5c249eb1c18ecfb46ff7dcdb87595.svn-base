package com.wzlue.goods.service;

import com.wzlue.goods.entity.SpecEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品规格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:42:51
 */
public interface SpecService {
	
	SpecEntity queryObject(Long id);
	
	List<SpecEntity> queryList(Map<String, Object> map);

	List<SpecEntity> queryList2(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);

	int queryTotal2(Map<String, Object> map);
	
	void save(SpecEntity spec);
	
	void update(SpecEntity spec);
	
	void delete(Long id);

	void del(Long id);
	
	void deleteBatch(Long[] ids);
	List<SpecEntity> selectPid(Map<String, Object> map);
	int queryTotalTwo(Map<String, Object> map);
	List<SpecEntity> selectIdSon(Integer id);
}
