package com.wzlue.goods.dao;

import com.wzlue.goods.entity.GoodsCollectionEntity;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.common.base.BaseDao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 商品收藏
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
@Mapper
public interface GoodsCollectionDao extends BaseDao<GoodsCollectionEntity> {

	List<GoodsCollectionEntity> getCollectionGoods(Map<String, Object> map);

	void deleteByGoodsAndMember(@Param("goodsId") Long goodsId, @Param("memberId") Long memberId);

}
