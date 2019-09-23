package com.wzlue.member.service;

import com.wzlue.member.entity.MemberInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 用户详情
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
public interface MemberInfoService {
	
	MemberInfoEntity queryObject(Long id);
	
	List<MemberInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MemberInfoEntity memberInfo);
	
	void update(MemberInfoEntity memberInfo);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

	MemberInfoEntity queryByOpenid(String openid);

	void signIn(Long userId);

	boolean isSignIn(Long userId);

	Integer queryIntegral(Long userId);
	void activateBatch(Long[] ids);
}
