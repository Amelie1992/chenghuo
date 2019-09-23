package com.wzlue.sys.dao;

import com.wzlue.sys.entity.SysAreaEntity;
import com.wzlue.common.base.BaseDao;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 
 * 
 * @author YJ
 * @email wzlue.com
 * @date 2018-06-11 14:13:34
 */
@Mapper
public interface SysAreaDao extends BaseDao<SysAreaEntity> {


    List<SysAreaEntity> areaList(Map<String,Object> params);
}
