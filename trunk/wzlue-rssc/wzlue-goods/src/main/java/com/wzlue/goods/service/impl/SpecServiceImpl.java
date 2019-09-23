package com.wzlue.goods.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.goods.dao.SpecDao;
import com.wzlue.goods.entity.SpecEntity;
import com.wzlue.goods.service.SpecService;



@Service("specService")
public class SpecServiceImpl implements SpecService {
	@Autowired
	private SpecDao specDao;
	
	@Override
	public SpecEntity queryObject(Long id){
		return specDao.queryObject(id);
	}
	
	@Override
	public List<SpecEntity> queryList(Map<String, Object> map){
		return specDao.queryList(map);
	}

	@Override
	public List<SpecEntity> queryList2(Map<String, Object> map) {
		return specDao.queryList2(map);
	}

	@Override
	public int queryTotal(Map<String, Object> map){
		return specDao.queryTotal(map);
	}

	@Override
	public int queryTotal2(Map<String, Object> map) {
		return specDao.queryTotal2(map);
	}

	@Override
	public void save(SpecEntity spec){
		specDao.save(spec);
	}
	
	@Override
	public void update(SpecEntity spec){
		specDao.update(spec);
	}
	
	@Override
	public void delete(Long id){
		specDao.delete(id);
	}

	@Override
	public void del(Long id) {
		specDao.del(id);
	}

	@Override
	public void deleteBatch(Long[] ids){
		specDao.deleteBatch(ids);
	}

	@Override
	public List<SpecEntity> selectPid(Map<String, Object> map) {
		return specDao.selectPid(map);
	}

	@Override
	public int queryTotalTwo(Map<String, Object> map) {
		return specDao.queryTotalTwo(map);
	}

	@Override
	public List<SpecEntity> selectIdSon(Integer id) {
		return specDao.selectIdSon(id);
	}

}
