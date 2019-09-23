package com.wzlue.menu.service.impl;

import com.wzlue.menu.utils.EscapeUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.wzlue.menu.dao.CustomMenuDao;
import com.wzlue.menu.entity.CustomMenuEntity;
import com.wzlue.menu.service.CustomMenuService;


@Service("customMenuService")
public class CustomMenuServiceImpl implements CustomMenuService {
    @Autowired
    private CustomMenuDao customMenuDao;

    @Override
    public CustomMenuEntity queryObject(Long id) {
        return customMenuDao.queryObject(id);
    }

    @Override
    public List<CustomMenuEntity> queryList(Map<String, Object> map) {
        List<CustomMenuEntity> menuList = customMenuDao.queryList(map);
       /* if (null != menuList && menuList.size() > 0) {
            for (CustomMenuEntity menu : menuList) {
		        if (StringUtils.isNotBlank(menu.getBody())) {
                    menu.setBody(EscapeUtil.unescape(menu.getBody()));
                }
            }
        }*/
        return menuList;
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return customMenuDao.queryTotal(map);
    }

    @Override
    public void save(CustomMenuEntity customMenu) {
        customMenuDao.save(customMenu);
    }

    @Override
    public void update(CustomMenuEntity customMenu) {
        customMenuDao.update(customMenu);
    }

    @Override
    public void delete(Long id) {
        customMenuDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        customMenuDao.deleteBatch(ids);
    }

    @Override
    public void show(Long id) {
        CustomMenuEntity customMenuEntity = new CustomMenuEntity();
        customMenuEntity.setState(1);
        customMenuEntity.setId(id);
        customMenuDao.update(customMenuEntity);
        customMenuDao.hide(id);
    }
}
