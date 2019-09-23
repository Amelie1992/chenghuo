package com.wzlue.wbiao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 成色
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
public class ConditionEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//成色名称
	private String conditionName;
	//描述
	private String description;
	//排序
	private Integer sort;

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
	 * 设置：成色名称
	 */
	public void setConditionName(String conditionName) {
		this.conditionName = conditionName;
	}
	/**
	 * 获取：成色名称
	 */
	public String getConditionName() {
		return conditionName;
	}
	/**
	 * 设置：描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Integer getSort() {
		return sort;
	}
}
