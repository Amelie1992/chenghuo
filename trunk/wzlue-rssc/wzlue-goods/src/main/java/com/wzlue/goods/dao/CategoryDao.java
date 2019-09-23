package com.wzlue.goods.dao;

import com.wzlue.goods.entity.CategoryEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 分类
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
@Mapper
public interface CategoryDao extends BaseDao<CategoryEntity> {
    int queryTotalName(String categoryName);

}
