package com.wzlue.integral.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 积分充值卡批次
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 14:48:04
 */
public class IntegralCardBatchEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//批次号
	private String batchNumber;
	//积分面值
	private Integer integral;
	//开卡数
	private Integer number;
	//创建时间
	private Date createTime;
	//备注
	private String remarks;

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
	 * 设置：批次号
	 */
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	/**
	 * 获取：批次号
	 */
	public String getBatchNumber() {
		return batchNumber;
	}
	/**
	 * 设置：积分面值
	 */
	public void setIntegral(Integer integral) {
		this.integral = integral;
	}
	/**
	 * 获取：积分面值
	 */
	public Integer getIntegral() {
		return integral;
	}
	/**
	 * 设置：开卡数
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	/**
	 * 获取：开卡数
	 */
	public Integer getNumber() {
		return number;
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
	 * 设置：备注
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	/**
	 * 获取：备注
	 */
	public String getRemarks() {
		return remarks;
	}
}
