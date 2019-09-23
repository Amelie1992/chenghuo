package com.wzlue.order.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 订单评价
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-08-22 16:04:16
 */
public class EvaluateEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//会员id
	private Long memberId;
	//订单编号
	private String orderNumber;
	//内容
	private String content;
	//评价
	private Integer evaluate;
	//时间
	private Date createTime;

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
	 * 设置：内容
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：内容
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：评价
	 */
	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}
	/**
	 * 获取：评价
	 */
	public Integer getEvaluate() {
		return evaluate;
	}
	/**
	 * 设置：时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
