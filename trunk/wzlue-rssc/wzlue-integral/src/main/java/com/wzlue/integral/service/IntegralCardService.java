package com.wzlue.integral.service;

import com.wzlue.integral.entity.IntegralCardEntity;

import java.util.List;
import java.util.Map;

/**
 * 积分充值卡
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 17:43:00
 */
public interface IntegralCardService {
	
	IntegralCardEntity queryObject(Long id);
	
	List<IntegralCardEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(IntegralCardEntity integralCard);
	
	void update(IntegralCardEntity integralCard);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);

    void activateBatch(Long[] ids);

    IntegralCardEntity queryByCardNumber(String cardNumber);

	void recharge(Long memberId, IntegralCardEntity integralCard);
}
