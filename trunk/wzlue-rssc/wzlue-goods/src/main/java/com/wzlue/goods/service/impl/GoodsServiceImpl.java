package com.wzlue.goods.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wzlue.goods.dao.FreightDao;
import com.wzlue.goods.dao.FreightTemplateDao;
import com.wzlue.goods.dao.GoodsDao;
import com.wzlue.goods.dao.GoodsPicDao;
import com.wzlue.goods.dao.GoodsPropertyDao;
import com.wzlue.goods.dao.GoodsTagDao;
import com.wzlue.goods.entity.FreightEntity;
import com.wzlue.goods.entity.FreightTemplateEntity;
import com.wzlue.goods.entity.GoodsEntity;
import com.wzlue.goods.entity.GoodsPicEntity;
import com.wzlue.goods.entity.GoodsPropertyEntity;
import com.wzlue.goods.entity.GoodsTagEntity;
import com.wzlue.goods.entity.TagEntity;
import com.wzlue.goods.service.GoodsService;


@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private GoodsPicDao goodsPicDao;
    @Autowired
    private GoodsPropertyDao goodsPropertyDao;
    @Autowired
    private GoodsTagDao goodsTagDao;
    @Autowired
    private FreightTemplateDao freightTemplateDao;
    @Autowired
    private FreightDao freightDao;

    @Override
    public GoodsEntity queryObject(Long id) {
        GoodsEntity goods = goodsDao.queryObject(id);
        if(goods.getFreightTemplateId() != null){
        	FreightTemplateEntity freightTemplate = freightTemplateDao.queryObject(goods.getFreightTemplateId());
        	Map<String, Object> map = new HashMap<String, Object>();
    		map.put("templateId", freightTemplate.getId());
    		List<FreightEntity> freightList = freightDao.queryList(map);
    		freightTemplate.setFreightList(freightList);
        	goods.setFreightTemplate(freightTemplate);
        }
        goods.setPicUrls(goodsPicDao.getPicUrlByGoodsId(id));
        return goods;
    }

    @Override
    public List<GoodsEntity> queryList(Map<String, Object> map) {
        return goodsDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return goodsDao.queryTotal(map);
    }

    @Transactional
    @Override
    public void save(GoodsEntity goods) {
         if(goods.getPrice()==null){
             goods.setPrice(BigDecimal.valueOf(0));
         }
         if(goods.getVipPrice()==null){
             goods.setVipPrice(BigDecimal.valueOf(0));
         }
        //step1 保存商品信息
        goodsDao.save(goods);

        //step2 保存商品轮播图片
        for (String picUrl : goods.getPicUrls()) {
            GoodsPicEntity goodsPicEntity = new GoodsPicEntity();
            goodsPicEntity.setGoodsId(goods.getId());
            goodsPicEntity.setPicUrl(picUrl);
            goodsPicDao.save(goodsPicEntity);
        }
        
        //保存属性
        List<GoodsPropertyEntity> goodsPropertyList =  goods.getGoodsPropertyList();
        for (GoodsPropertyEntity goodsProperty : goodsPropertyList) {
			goodsProperty.setGoodsId(goods.getId());
			goodsPropertyDao.save(goodsProperty);
		}
        
        //保存标签
        List<TagEntity> tagList = goods.getTagList();
        for (TagEntity tag : tagList) {
        	GoodsTagEntity goodsTag = new GoodsTagEntity();
        	goodsTag.setGoodsId(goods.getId());
        	goodsTag.setTagId(tag.getId());
        	goodsTagDao.save(goodsTag);
		}
        
    }

    @Transactional
    @Override
    public void update(GoodsEntity goods) {
        //step1 修改商品信息
        goodsDao.update(goods);
        //step2 删除商品图片
        goodsPicDao.deleteByGoodsId(goods.getId());
        //step3 保存商品轮播图片
        for (String picUrl : goods.getPicUrls()) {
            GoodsPicEntity goodsPicEntity = new GoodsPicEntity();
            goodsPicEntity.setGoodsId(goods.getId());
            goodsPicEntity.setPicUrl(picUrl);
            goodsPicDao.save(goodsPicEntity);
        }
        
        goodsPropertyDao.deleteByGoodsId(goods.getId());
        
        //保存属性
        List<GoodsPropertyEntity> goodsPropertyList =  goods.getGoodsPropertyList();
        for (GoodsPropertyEntity goodsProperty : goodsPropertyList) {
			goodsProperty.setGoodsId(goods.getId());
			goodsPropertyDao.save(goodsProperty);
		}
        
        goodsTagDao.deleteByGoodsId(goods.getId());
        //保存标签
        List<TagEntity> tagList = goods.getTagList();
        for (TagEntity tag : tagList) {
        	GoodsTagEntity goodsTag = new GoodsTagEntity();
        	goodsTag.setGoodsId(goods.getId());
        	goodsTag.setTagId(tag.getId());
        	goodsTagDao.save(goodsTag);
		}
    }

    @Override
    public void delete(Long id) {
        goodsDao.delete(id);
    }

    @Override
    public void deleteBatch(Long[] ids) {
        //step1 删除商品信息
        goodsDao.deleteBatch(ids);
    }

	@Override
	public void updateStatus(Long[] ids, Integer status) {
		// TODO Auto-generated method stub
		goodsDao.updateStatus(ids, status);
	}

}
