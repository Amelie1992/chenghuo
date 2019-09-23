package com.wzlue.advert.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商户推广海报表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:18:34
 */
public class ExtensionPosterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//推广海报
	private String posterUrl;
	//状态（1正常2禁用）
	private Integer status;
	//添加时间
	private Date addTime;
	//更新时间
	private Date updateTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：推广海报
	 */
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	/**
	 * 获取：推广海报
	 */
	public String getPosterUrl() {
		return posterUrl;
	}
	/**
	 * 设置：状态（1正常2禁用）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态（1正常2禁用）
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
