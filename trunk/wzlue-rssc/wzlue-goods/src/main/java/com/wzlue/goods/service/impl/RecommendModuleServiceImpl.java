package com.wzlue.goods.service.impl;

import com.wzlue.goods.dao.RecommendGoodsDao;
import com.wzlue.goods.dao.RecommendModuleDao;
import com.wzlue.goods.entity.RecommendModuleEntity;
import com.wzlue.goods.service.RecommendModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("recommendModuleService")
public class RecommendModuleServiceImpl implements RecommendModuleService {
	@Autowired
	private RecommendModuleDao recommendModuleDao;

	@Autowired
	private RecommendGoodsDao recommendGoodsDao;


	@Override
	public RecommendModuleEntity queryObject(Long id){
		return recommendModuleDao.queryObject(id);
	}
	
	@Override
	public List<RecommendModuleEntity> queryList(Map<String, Object> map){
		return recommendModuleDao.queryList(map);
	}

	@Override
	public List<RecommendModuleEntity> queryListApi(Map<String, Object> map) {
		return recommendModuleDao.queryListApi(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return recommendModuleDao.queryTotal(map);
	}
	
	@Override
	public void save(RecommendModuleEntity gRecommendModule){
		recommendModuleDao.save(gRecommendModule);
	}
	
	@Override
	public void update(RecommendModuleEntity gRecommendModule){
		gRecommendModule.setUpdateTime(new Date());
		recommendModuleDao.update(gRecommendModule);
	}
	
	@Override
	public void delete(Long id){
		recommendModuleDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		for (int i = 0; i < ids.length; i++) {
			//同时删除二级
			recommendGoodsDao.deleteBymoduleId(ids[i]);
		}
		recommendModuleDao.deleteBatch(ids);
	}
	
}
