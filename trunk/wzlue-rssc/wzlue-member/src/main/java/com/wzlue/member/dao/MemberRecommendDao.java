package com.wzlue.member.dao;

import com.wzlue.common.base.BaseDao;
import com.wzlue.member.entity.MemberRecommendEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员推荐人列表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-18 16:57:45
 */
@Mapper
public interface MemberRecommendDao extends BaseDao<MemberRecommendEntity> {
	
}
