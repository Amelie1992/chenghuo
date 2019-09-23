package com.wzlue.member.service;

import com.wzlue.member.entity.InvoiceEntity;

import java.util.List;
import java.util.Map;

/**
 * 发票
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-04 15:13:35
 */
public interface InvoiceService {
	
	InvoiceEntity queryObject(Long id);
	
	List<InvoiceEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(InvoiceEntity invoice);
	
	void update(InvoiceEntity invoice);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
