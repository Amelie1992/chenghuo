package com.wzlue.goods.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wzlue.goods.dao.FreightDao;
import com.wzlue.goods.dao.FreightTemplateDao;
import com.wzlue.goods.entity.FreightEntity;
import com.wzlue.goods.entity.FreightTemplateEntity;
import com.wzlue.goods.service.FreightTemplateService;



@Service("freightTemplateService")
public class FreightTemplateServiceImpl implements FreightTemplateService {
	@Autowired
	private FreightTemplateDao freightTemplateDao;
	@Autowired
	private FreightDao freightDao;
	
	@Override
	public FreightTemplateEntity queryObject(Integer id){
		FreightTemplateEntity freightTemplate = freightTemplateDao.queryObject(id);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("templateId", freightTemplate.getId());
		List<FreightEntity> freightList = freightDao.queryList(map);
		freightTemplate.setFreightList(freightList);
		return freightTemplate;
	}
	
	@Override
	public List<FreightTemplateEntity> queryList(Map<String, Object> map){
		return freightTemplateDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return freightTemplateDao.queryTotal(map);
	}
	
	@Override
	public void save(FreightTemplateEntity freightTemplate){
		freightTemplateDao.save(freightTemplate);
		
		List<FreightEntity> freightList = freightTemplate.getFreightList();
		for (FreightEntity freightEntity : freightList) {
			freightEntity.setTemplateId(freightTemplate.getId());
			freightDao.save(freightEntity);
		}
		
	}
	
	@Override
	public void update(FreightTemplateEntity freightTemplate){
		freightTemplateDao.update(freightTemplate);
		
		freightDao.deleteByTemplateId(freightTemplate.getId());
		
		List<FreightEntity> freightList = freightTemplate.getFreightList();
		for (FreightEntity freightEntity : freightList) {
			freightEntity.setTemplateId(freightTemplate.getId());
			freightDao.save(freightEntity);
		}
	}
	
	@Override
	public void delete(Integer id){
		freightTemplateDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		freightTemplateDao.deleteBatch(ids);
	}
	
}
