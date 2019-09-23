package com.wzlue.goods.dao;

import com.wzlue.goods.entity.GoodsSpecEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 商品规格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@Mapper
public interface GoodsSpecDao extends BaseDao<GoodsSpecEntity> {

    List<GoodsSpecEntity> getSpecs(Long id);
    GoodsSpecEntity querySpecPrice(Map<String, Object> map);


    List<GoodsSpecEntity> goodSpecId(@Param("ids") String ids);

	
}
