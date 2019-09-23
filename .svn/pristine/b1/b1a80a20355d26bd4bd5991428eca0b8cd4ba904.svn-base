package com.wzlue.member.dao;

import com.wzlue.member.entity.MemberAddressEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户收货表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
@Mapper
public interface MemberAddressDao extends BaseDao<MemberAddressEntity> {

	void updateDefault(MemberAddressEntity memberAddress);

	MemberAddressEntity queryDefault(Long userId);
	
}
