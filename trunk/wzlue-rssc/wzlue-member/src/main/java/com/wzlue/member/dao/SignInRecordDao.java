package com.wzlue.member.dao;

import com.wzlue.member.entity.SignInRecordEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 签到记录
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-02 16:15:55
 */
@Mapper
public interface SignInRecordDao extends BaseDao<SignInRecordEntity> {
	
}
