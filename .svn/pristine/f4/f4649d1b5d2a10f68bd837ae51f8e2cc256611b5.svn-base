package com.wzlue.member.dao;

import com.wzlue.member.entity.IntegralRecordEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 积分记录
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-26 11:19:34
 */
@Mapper
public interface IntegralRecordDao extends BaseDao<IntegralRecordEntity> {
    List<IntegralRecordEntity> queryListTwo(Map<String, Object> map);
    int queryCount(@Param("daId") Long daId,@Param("xiaoId") Long xiaoId);
	
}
