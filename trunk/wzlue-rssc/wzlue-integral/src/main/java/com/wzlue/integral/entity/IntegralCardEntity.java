package com.wzlue.integral.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 积分充值卡
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-03 17:43:00
 */
public class IntegralCardEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//充值码/卡号
	private String cardNumber;
	//分类：1实体卡；2虚拟商品
	private Integer type;
	//批次号(实体卡才有批次号)
	private String batchNumber;
	//积分面值
	private Integer integral;
	//激活状态：1激活；2未激活
	private Integer activationState;
	//激活时间
	private Date activationTime;
	//核销状态：1核销；2未核销
	private Integer writeOffStatus;
	//充值人
	private Long rechargeBy;
	private String rechargeByName;
	//充值时间
	private Date rechargeTime;
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
	 * 设置：充值码/卡号
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * 获取：充值码/卡号
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * 设置：分类：1实体卡；2虚拟商品
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：分类：1实体卡；2虚拟商品
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：批次号(实体卡才有批次号)
	 */
	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}
	/**
	 * 获取：批次号(实体卡才有批次号)
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
	 * 设置：激活状态：1激活；2未激活
	 */
	public void setActivationState(Integer activationState) {
		this.activationState = activationState;
	}
	/**
	 * 获取：激活状态：1激活；2未激活
	 */
	public Integer getActivationState() {
		return activationState;
	}

	public Date getActivationTime() {
		return activationTime;
	}

	public void setActivationTime(Date activationTime) {
		this.activationTime = activationTime;
	}

	/**
	 * 设置：核销状态：1核销；2未核销
	 */
	public void setWriteOffStatus(Integer writeOffStatus) {
		this.writeOffStatus = writeOffStatus;
	}
	/**
	 * 获取：核销状态：1核销；2未核销
	 */
	public Integer getWriteOffStatus() {
		return writeOffStatus;
	}
	/**
	 * 设置：充值人
	 */
	public void setRechargeBy(Long rechargeBy) {
		this.rechargeBy = rechargeBy;
	}
	/**
	 * 获取：充值人
	 */
	public Long getRechargeBy() {
		return rechargeBy;
	}

	public String getRechargeByName() {
		return rechargeByName;
	}

	public void setRechargeByName(String rechargeByName) {
		this.rechargeByName = rechargeByName;
	}

	/**
	 * 设置：充值时间
	 */
	public void setRechargeTime(Date rechargeTime) {
		this.rechargeTime = rechargeTime;
	}
	/**
	 * 获取：充值时间
	 */
	public Date getRechargeTime() {
		return rechargeTime;
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
