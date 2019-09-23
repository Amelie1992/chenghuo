package com.wzlue.goods.service.impl;

import com.wzlue.goods.dao.RecommendGoodsDao;
import com.wzlue.goods.entity.RecommendGoodsEntity;
import com.wzlue.goods.service.RecommendGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("recommendGoodsService")
public class RecommendGoodsServiceImpl implements RecommendGoodsService {
	@Autowired
	private RecommendGoodsDao recommendGoodsDao;
	
	@Override
	public RecommendGoodsEntity queryObject(Long id){
		return recommendGoodsDao.queryObject(id);
	}
	
	@Override
	public List<RecommendGoodsEntity> queryList(Map<String, Object> map){
		return recommendGoodsDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return recommendGoodsDao.queryTotal(map);
	}
	
	@Override
	public void save(RecommendGoodsEntity gRecommendGoods){
		recommendGoodsDao.save(gRecommendGoods);
	}
	
	@Override
	public void update(RecommendGoodsEntity gRecommendGoods){
		gRecommendGoods.setUpdateTime(new Date());
		recommendGoodsDao.update(gRecommendGoods);
	}
	
	@Override
	public void delete(Long id){
		recommendGoodsDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		recommendGoodsDao.deleteBatch(ids);
	}
	
}
