package com.wzlue.goods.dao;

import com.wzlue.common.base.BaseDao;
import com.wzlue.goods.entity.RecommendGoodsEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 推荐商品表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:02:57
 */
@Mapper
public interface RecommendGoodsDao extends BaseDao<RecommendGoodsEntity> {

	//id查商品信息
	List<RecommendGoodsEntity> queryListApi(@Param(value="moduleId") Long moduleId);
	//根据上级id删除
	int deleteBymoduleId(@Param(value="moduleId") Long moduleId);
}
