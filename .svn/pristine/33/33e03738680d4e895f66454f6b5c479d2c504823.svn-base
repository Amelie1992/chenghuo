package com.wzlue.contact.service.impl;

import com.wzlue.contact.service.ContactService;
import com.wzlue.contact.dao.ContactDao;
import com.wzlue.contact.entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;




@Service("wContactService")
public class ContactServiceImpl implements ContactService {
	@Autowired
	private ContactDao wContactDao;
	
	@Override
	public ContactEntity queryObject(Long id){
		return wContactDao.queryObject(id);
	}
	
	@Override
	public List<ContactEntity> queryList(Map<String, Object> map){
		return wContactDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return wContactDao.queryTotal(map);
	}
	
	@Override
	public void save(ContactEntity wContact){
		wContactDao.save(wContact);
	}
	
	@Override
	public void update(ContactEntity wContact){
		wContactDao.update(wContact);
	}
	
	@Override
	public void delete(Long id){
		wContactDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		wContactDao.deleteBatch(ids);
	}
	
}
