package com.wzlue.member.dao;

import com.wzlue.member.entity.MemberInfoEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户详情
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
@Mapper
public interface MemberInfoDao extends BaseDao<MemberInfoEntity> {

	MemberInfoEntity queryByOpenid(String openid);

	void addIntegral(@Param("id") Long id, @Param("get") Integer get);

	Integer queryIntegral(Long userId);
	void activateBatch(Long[] ids);
	void activateBatchTwo(Long[] ids);
	int zongCount(@Param("isVip")String isVip);
	
}
