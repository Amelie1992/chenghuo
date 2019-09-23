package com.wzlue.advert.dao;

import com.wzlue.advert.entity.AdvertEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 广告
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 15:18:40
 */
@Mapper
public interface AdvertDao extends BaseDao<AdvertEntity> {
	
}
