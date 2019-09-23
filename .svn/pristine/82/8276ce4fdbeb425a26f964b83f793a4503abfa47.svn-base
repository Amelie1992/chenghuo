package com.wzlue.goods.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzlue.goods.dao.GoodsCollectionDao;
import com.wzlue.goods.entity.GoodsCollectionEntity;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.service.GoodsCollectionService;



@Service("goodsCollectionService")
public class GoodsCollectionServiceImpl implements GoodsCollectionService {
	@Autowired
	private GoodsCollectionDao goodsCollectionDao;
	
	@Override
	public GoodsCollectionEntity queryObject(Long id){
		return goodsCollectionDao.queryObject(id);
	}
	
	@Override
	public List<GoodsCollectionEntity> queryList(Map<String, Object> map){
		return goodsCollectionDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsCollectionDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsCollectionEntity goodsCollection){
		goodsCollectionDao.save(goodsCollection);
	}
	
	@Override
	public void update(GoodsCollectionEntity goodsCollection){
		goodsCollectionDao.update(goodsCollection);
	}
	
	@Override
	public void delete(Long id){
		goodsCollectionDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsCollectionDao.deleteBatch(ids);
	}

	@Override
	public void collection(Long id, Long userId) {
		GoodsCollectionEntity goodsCollection = new GoodsCollectionEntity();
		goodsCollection.setGoodsId(id);
		goodsCollection.setMemberId(userId);
		goodsCollection.setCreateTime(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", userId);
		map.put("goodsId", id);
		
		int total = goodsCollectionDao.queryTotal(map);
		if(total == 0){
			goodsCollectionDao.save(goodsCollection);
		}
	}

	@Override
	public List<GoodsCollectionEntity> getCollectionGoods(Map<String, Object> map) {
		return goodsCollectionDao.getCollectionGoods(map);
	}


	@Override
	public void deleteByGoodsAndMember(Long goodsId, Long memberId) {
		goodsCollectionDao.deleteByGoodsAndMember(goodsId, memberId);
		
	}
	
}
