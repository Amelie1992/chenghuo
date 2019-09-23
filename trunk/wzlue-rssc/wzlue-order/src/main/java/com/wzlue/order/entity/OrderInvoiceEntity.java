package com.wzlue.order.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单发票
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-15 10:36:04
 */
public class OrderInvoiceEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Integer id;
	//订单编号
	private String orderNumber;
	//发票类型(0单位1个人)
	private Integer invoiceType;
	//发票抬头
	private String invoiceTitle;
	//税号
	private String taxNumber;

	/**
	 * 设置：主键
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：主键
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：订单编号
	 */
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	/**
	 * 获取：订单编号
	 */
	public String getOrderNumber() {
		return orderNumber;
	}
	/**
	 * 设置：发票类型
	 */
	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}
	/**
	 * 获取：发票类型
	 */
	public Integer getInvoiceType() {
		return invoiceType;
	}
	/**
	 * 设置：发票抬头
	 */
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	/**
	 * 获取：发票抬头
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
