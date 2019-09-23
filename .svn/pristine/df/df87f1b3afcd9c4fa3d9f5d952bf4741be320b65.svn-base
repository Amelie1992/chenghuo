package com.wzlue.advert.dao;


import com.wzlue.advert.entity.ExtensionPosterEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 商户推广海报表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:18:34
 */
@Mapper
public interface ExtensionPosterDao extends BaseDao<ExtensionPosterEntity> {
	List<ExtensionPosterEntity> queryListApi(Map<String, Object> map);

	//除当前之外的推广海报
	List<ExtensionPosterEntity> queryOther(Map<String, Object> map);
}
