package com.wzlue.goods.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * 推荐商品模块表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 10:06:35
 */
public class RecommendModuleEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//模块名
	private String moduleName;
	//排序
	private Long sort;
	//状态：1正常，2禁用
	private Integer status;
	//添加时间
	private Date addTime;
	//更新时间
	private Date updateTime;

	//关联商品list
	private List<RecommendGoodsEntity> recommendGoodsEntities;

	public List<RecommendGoodsEntity> getRecommendGoodsEntities() {
		return recommendGoodsEntities;
	}

	public void setRecommendGoodsEntities(List<RecommendGoodsEntity> recommendGoodsEntities) {
		this.recommendGoodsEntities = recommendGoodsEntities;
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
	 * 设置：模块名
	 */
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	/**
	 * 获取：模块名
	 */
	public String getModuleName() {
		return moduleName;
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
	 * 设置：状态：1正常，2禁用
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1正常，2禁用
	 */
	public Integer getStatus() {
		return status;
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
