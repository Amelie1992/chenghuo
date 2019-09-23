package com.wzlue.member.service;


import com.wzlue.member.entity.MemberRecommendEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员推荐人列表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-18 16:57:45
 */
public interface MemberRecommendService {
	
	MemberRecommendEntity queryObject(Long id);
	
	List<MemberRecommendEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MemberRecommendEntity mMemberRecommend);
	//购买会员成功后添加推广记录
	void add(Long memberId,Long recommenderId);
	
	void update(MemberRecommendEntity mMemberRecommend);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
