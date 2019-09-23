package com.wzlue.goods.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzlue.goods.dao.GoodsFootprintDao;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.entity.GoodsFootprintEntity;
import com.wzlue.goods.service.GoodsFootprintService;



@Service("goodsFootprintService")
public class GoodsFootprintServiceImpl implements GoodsFootprintService {
	@Autowired
	private GoodsFootprintDao goodsFootprintDao;
	
	@Override
	public GoodsFootprintEntity queryObject(Long id){
		return goodsFootprintDao.queryObject(id);
	}
	
	@Override
	public List<GoodsFootprintEntity> queryList(Map<String, Object> map){
		return goodsFootprintDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return goodsFootprintDao.queryTotal(map);
	}
	
	@Override
	public void save(GoodsFootprintEntity goodsFootprint){
		goodsFootprintDao.save(goodsFootprint);
	}
	
	@Override
	public void update(GoodsFootprintEntity goodsFootprint){
		goodsFootprintDao.update(goodsFootprint);
	}
	
	@Override
	public void delete(Long id){
		goodsFootprintDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		goodsFootprintDao.deleteBatch(ids);
	}

	@Override
	public void footprint(Long id, Long userId) {
		GoodsFootprintEntity goodsFootprint = new GoodsFootprintEntity();
		goodsFootprint.setGoodsId(id);
		goodsFootprint.setMemberId(userId);
		goodsFootprint.setCreateTime(new Date());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("goodsId", id);
		map.put("memberId", userId);
		int total = goodsFootprintDao.queryTotal(map);
		if(total == 0){
			goodsFootprintDao.save(goodsFootprint);
		}
	}

	@Override
	public List<GoodsEntity> getFootprintGoods(Map<String, Object> map) {
		return goodsFootprintDao.getFootprintGoods(map);
	}

	@Override
	public void deleteByGoodsAndMember(Long goodsId, Long memberId) {
		goodsFootprintDao.deleteByGoodsAndMember(goodsId, memberId);
	}
	
}
