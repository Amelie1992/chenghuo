package com.wzlue.goods.dao;

import com.wzlue.goods.entity.GoodsPicEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 商品图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@Mapper
public interface GoodsPicDao extends BaseDao<GoodsPicEntity> {
	 String[] getPicUrlByGoodsId(Long goodsId);

	List<GoodsPicEntity> getPicImgs(Long goodsId);
	//批量删除商品图片
	void deleteByGoodsId(Long id);
}
