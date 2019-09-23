package com.wzlue.member.service;

import com.wzlue.member.entity.MemberAgreementEntity;

import java.util.List;
import java.util.Map;

/**
 * 会员协议
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-04 14:08:30
 */
public interface MemberAgreementService {
	
	MemberAgreementEntity queryObject(Long id);
	
	List<MemberAgreementEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(MemberAgreementEntity memberAgreement);
	
	void update(MemberAgreementEntity memberAgreement);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
