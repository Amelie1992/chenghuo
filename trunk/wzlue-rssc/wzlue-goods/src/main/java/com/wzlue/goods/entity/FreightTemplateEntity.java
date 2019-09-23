package com.wzlue.goods.entity;

import java.io.Serializable;
import java.util.List;


/**
 * 运费模板
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-09-17 15:22:30
 */
public class FreightTemplateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//模板名称
	private String templateName;
	
	private List<FreightEntity> freightList;
	
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
	 * 设置：模板名称
	 */
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	/**
	 * 获取：模板名称
	 */
	public String getTemplateName() {
		return templateName;
	}
	
	public List<FreightEntity> getFreightList() {
		return freightList;
	}
	
	public void setFreightList(List<FreightEntity> freightList) {
		this.freightList = freightList;
	}
	
}
