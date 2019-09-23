package com.wzlue.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 推荐商品表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:02:57
 */
public class RecommendGoodsEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//模块ID
	private Long moduleId;
	//商品ID
	private Long goodsId;
	//排序
	private Long sort;
	//添加时间
	private Date addTime;
	//更新时间
	private Date updateTime;

	//商品名
	private String goodsName;
	//商品图片
	private String picUrl;
	//实际交易价格
	private BigDecimal payPrice;
	//模块名
	private String moduleName;

	//模块商品集合
	List<RecommendGoodsEntity> recommendGoodsList;

	//商品表
	GoodsEntity goodsEntity;

	public GoodsEntity getGoodsEntity() {
		return goodsEntity;
	}

	public void setGoodsEntity(GoodsEntity goodsEntity) {
		this.goodsEntity = goodsEntity;
	}

	public BigDecimal getPayPrice() {
		return payPrice;
	}

	public void setPayPrice(BigDecimal payPrice) {
		this.payPrice = payPrice;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public List<RecommendGoodsEntity> getRecommendGoodsList() {
		return recommendGoodsList;
	}

	public void setRecommendGoodsList(List<RecommendGoodsEntity> recommendGoodsList) {
		this.recommendGoodsList = recommendGoodsList;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	/**
	 * 设置：主键
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：模块ID
	 */
	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}
	/**
	 * 获取：模块ID
	 */
	public Long getModuleId() {
		return moduleId;
	}
	/**
	 * 设置：商品ID
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品ID
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Long getSort() {
		return sort;
	}
	/**
	 * 设置：添加时间
	 */
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
	/**
	 * 获取：添加时间
	 */
	public Date getAddTime() {
		return addTime;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
}
