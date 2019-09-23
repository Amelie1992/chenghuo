package com.wzlue.advert.service.impl;

import com.wzlue.advert.dao.ExtensionPosterDao;
import com.wzlue.advert.entity.ExtensionPosterEntity;
import com.wzlue.advert.service.ExtensionPosterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Service("extensionPosterService")
public class ExtensionPosterServiceImpl implements ExtensionPosterService {
	@Autowired
	private ExtensionPosterDao extensionPosterDao;
	
	@Override
	public ExtensionPosterEntity queryObject(Long id){
		return extensionPosterDao.queryObject(id);
	}
	
	@Override
	public List<ExtensionPosterEntity> queryList(Map<String, Object> map){
		return extensionPosterDao.queryList(map);
	}
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return extensionPosterDao.queryTotal(map);
	}
	
	@Override
	public void save(ExtensionPosterEntity extensionPoster){
		extensionPosterDao.save(extensionPoster);
		Map<String, Object> map = new HashMap<>();
		map.put("id",extensionPoster.getId());
		List<ExtensionPosterEntity> extensionPosterEntities = extensionPosterDao.queryOther(map);
		for (ExtensionPosterEntity ep:extensionPosterEntities) {
			ep.setStatus(2);
			extensionPosterDao.update(ep);
		}
	}
	
	@Override
	public void update(ExtensionPosterEntity extensionPoster){
		extensionPoster.setUpdateTime(new Date());
		//若当前启动其余改为禁用
		if (extensionPoster.getStatus()==1){
			Map<String, Object> map = new HashMap<>();
			map.put("id",extensionPoster.getId());
			List<ExtensionPosterEntity> extensionPosterEntities = extensionPosterDao.queryOther(map);
			for (ExtensionPosterEntity ep:extensionPosterEntities) {
				ep.setStatus(2);
				extensionPosterDao.update(ep);
			}
		}
		extensionPosterDao.update(extensionPoster);
	}
	
	@Override
	public void delete(Long id){
		extensionPosterDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Long[] ids){
		extensionPosterDao.deleteBatch(ids);
	}

	@Override
	public List<ExtensionPosterEntity> queryListApi(Map<String, Object> map) {
		return extensionPosterDao.queryListApi(map);
	}

}
