package com.wzlue.wbiao.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wzlue.goods.entity.BrandEntity;


/**
 * 回收置换
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 20:30:44
 */
public class RecoveryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//编号
	private String recoveryNumber;
	//品牌id
	private Long brandId;
	//成色id
	private Long conditionId;
	//状态：1处理中，2已处理
	private Integer status;
	//功能状态
	private String functionStatus;
	//申请时间
	private Date createTime;
	
	private Long memberId;
	
	private String[] picUrls;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date buyTime;
	
	private Integer maintain;
	
	private ConditionEntity condition;
	
	private BrandEntity brand;
	
	private String applyResult;

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
	 * 设置：编号
	 */
	public void setRecoveryNumber(String recoveryNumber) {
		this.recoveryNumber = recoveryNumber;
	}
	/**
	 * 获取：编号
	 */
	public String getRecoveryNumber() {
		return recoveryNumber;
	}
	/**
	 * 设置：品牌id
	 */
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取：品牌id
	 */
	public Long getBrandId() {
		return brandId;
	}
	public Long getConditionId() {
		return conditionId;
	}
	public void setConditionId(Long conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * 设置：状态：1处理中，2已处理
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态：1处理中，2已处理
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：功能状态
	 */
	public void setFunctionStatus(String functionStatus) {
		this.functionStatus = functionStatus;
	}
	/**
	 * 获取：功能状态
	 */
	public String getFunctionStatus() {
		return functionStatus;
	}
	/**
	 * 设置：申请时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：申请时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	public Long getMemberId() {
		return memberId;
	}
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	public String[] getPicUrls() {
		return picUrls;
	}
	public void setPicUrls(String[] picUrls) {
		this.picUrls = picUrls;
	}
	public Date getBuyTime() {
		return buyTime;
	}
	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}
	public Integer getMaintain() {
		return maintain;
	}
	public void setMaintain(Integer maintain) {
		this.maintain = maintain;
	}
	public ConditionEntity getCondition() {
		return condition;
	}
	public void setCondition(ConditionEntity condition) {
		this.condition = condition;
	}
	public BrandEntity getBrand() {
		return brand;
	}
	public void setBrand(BrandEntity brand) {
		this.brand = brand;
	}
	public String getApplyResult() {
		return applyResult == null? "": applyResult;
	}
	public void setApplyResult(String applyResult) {
		this.applyResult = applyResult;
	}
	
}
