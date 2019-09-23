package com.wzlue.contact.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 每日访问表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-08-13 17:39:33
 */
public class MMemberLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键id
	private Integer id;
	//用户id
	private Integer memberId;
	//微信openid
	private String openId;
	//访问时间
	private Date time;

	/**
	 * 设置：主键id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：用户id
	 */
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：用户id
	 */
	public Integer getMemberId() {
		return memberId;
	}
	/**
	 * 设置：微信openid
	 */
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	/**
	 * 获取：微信openid
	 */
	public String getOpenId() {
		return openId;
	}
	/**
	 * 设置：访问时间
	 */
	public void setTime(Date time) {
		this.time = time;
	}
	/**
	 * 获取：访问时间
	 */
	public Date getTime() {
		return time;
	}
}
