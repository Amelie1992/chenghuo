package com.wzlue.member.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 会员协议
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-04 14:08:30
 */
public class MemberAgreementEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//协议内容
	private String content;
	//状态（1正常2禁用）
	private Integer status;
	//创建时间
	private Date createTime;
	//更新时间
	private Date updateTime;

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
	 * 设置：协议内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：协议内容
	 */
	public String getContent() {
		return content;
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
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
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
