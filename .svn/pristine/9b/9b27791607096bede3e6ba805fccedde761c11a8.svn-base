package com.wzlue.goods.dao;

import com.wzlue.goods.entity.SpecEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商品规格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:42:51
 */
@Mapper
public interface SpecDao extends BaseDao<SpecEntity> {

	//根据父级ID查询子类列表
	List<SpecEntity> queryList2(Map<String, Object> map);

	int queryTotal2(Map<String, Object> map);

	//根据Id删除二级
	int del(Object id);
    List<SpecEntity> selectPid(Map<String, Object> map);
    int queryTotalTwo(Map<String, Object> map);
    List<SpecEntity> selectIdSon(Integer id);

	SpecEntity GoodsSpecEntity(Object id);

}
