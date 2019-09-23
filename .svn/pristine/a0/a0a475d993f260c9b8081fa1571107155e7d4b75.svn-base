package com.wzlue.contact.dao;

import com.wzlue.contact.entity.MMemberLogEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 每日访问表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-08-13 17:39:33
 */
@Mapper
public interface MMemberLogDao extends BaseDao<MMemberLogEntity> {
    int  queryTotalToday(@Param("memberInfoId") Long memberInfoId);
    int visitToday();
    int visitYeasterDay();
	
}
