package com.wzlue.wbiao.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 回收置换图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
public class RecoveryPicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//回收置换id
	private Long recoveryId;
	//图片
	private String picUrl;
	
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
	 * 设置：回收置换id
	 */
	public void setRecoveryId(Long recoveryId) {
		this.recoveryId = recoveryId;
	}
	/**
	 * 获取：回收置换id
	 */
	public Long getRecoveryId() {
		return recoveryId;
	}
	/**
	 * 设置：图片
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取：图片
	 */
	public String getPicUrl() {
		return picUrl;
	}
}
