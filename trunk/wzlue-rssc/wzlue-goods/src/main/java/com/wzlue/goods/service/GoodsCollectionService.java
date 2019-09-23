package com.wzlue.goods.service;

import com.wzlue.goods.entity.GoodsCollectionEntity;
import com.wzlue.goods.entity.GoodsEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品收藏
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
public interface GoodsCollectionService {
	
	GoodsCollectionEntity queryObject(Long id);
	
	List<GoodsCollectionEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(GoodsCollectionEntity goodsCollection);
	
	void update(GoodsCollectionEntity goodsCollection);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	void collection(Long id, Long userId);

	List<GoodsCollectionEntity> getCollectionGoods(Map<String, Object> map);

	void deleteByGoodsAndMember(Long goodsId, Long memberId);
}
