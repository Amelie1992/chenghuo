package com.wzlue.contact.service;

import com.wzlue.contact.entity.ContactEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-03-27 16:58:22
 */
public interface ContactService {
	
	ContactEntity queryObject(Long id);
	
	List<ContactEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ContactEntity wContact);
	
	void update(ContactEntity wContact);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
