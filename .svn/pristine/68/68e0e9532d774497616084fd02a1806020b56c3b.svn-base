package com.wzlue.member.service;

import com.wzlue.member.entity.SignInRecordEntity;

import java.util.List;
import java.util.Map;

/**
 * 签到记录
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-02 16:15:55
 */
public interface SignInRecordService {
	
	SignInRecordEntity queryObject(Long id);
	
	List<SignInRecordEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SignInRecordEntity signInRecord);
	
	void update(SignInRecordEntity signInRecord);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
