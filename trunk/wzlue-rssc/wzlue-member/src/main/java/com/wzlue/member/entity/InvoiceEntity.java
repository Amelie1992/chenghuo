package com.wzlue.member.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 发票
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-04 15:13:35
 */
public class InvoiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//会员id
	private Long memberId;
	//发票类别：1个人，2企业
	private Integer invoiceType;
	//抬头
	private String invoiceTitle;
	//税号
	private String taxNumber;

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
	 * 设置：会员id
	 */
	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}
	/**
	 * 获取：会员id
	 */
	public Long getMemberId() {
		return memberId;
	}
	/**
	 * 设置：发票类别：1个人，2企业
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 获取：发票类别：1个人，2企业
	 */
	public Integer getInvoiceType() {
		return invoiceType;
	}
	/**
	 * 设置：抬头
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	/**
	 * 获取：抬头
	 */
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	/**
	 * 设置：税号
	 */
	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}
	/**
	 * 获取：税号
	 */
	public String getTaxNumber() {
		return taxNumber;
	}
}
