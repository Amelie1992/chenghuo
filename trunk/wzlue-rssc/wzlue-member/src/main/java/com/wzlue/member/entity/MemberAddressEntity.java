package com.wzlue.member.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 用户收货表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:58:06
 */
public class MemberAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//会员id
	private Long memberId;
	//姓名
	private String userName;
	//省
	private String province;
	//市
	private String city;
	//区
	private String county;
	//街道
	private String street;
	//邮编
	private String zipCode;
	//手机号码
	private String telNumber;
	//默认收货地址：1是，0否
	private Integer defaultAddress;
	//0：未删除 1：已删除
	private Integer delFlag;

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
	 * 设置：姓名
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取：姓名
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * 设置：省
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * 获取：省
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * 设置：市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：市
	 */
	public String getCity() {
		return city;
	}
	/**
	 * 设置：区
	 */
	public void setCounty(String county) {
		this.county = county;
	}
	/**
	 * 获取：区
	 */
	public String getCounty() {
		return county;
	}
	/**
	 * 设置：街道
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：街道
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 设置：邮编
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * 获取：邮编
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * 设置：手机号码
	 */
	public void setTelNumber(String telNumber) {
		this.telNumber = telNumber;
	}
	/**
	 * 获取：手机号码
	 */
	public String getTelNumber() {
		return telNumber;
	}
	/**
	 * 设置：默认收货地址：1是，0否
	 */
	public void setDefaultAddress(Integer defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	/**
	 * 获取：默认收货地址：1是，0否
	 */
	public Integer getDefaultAddress() {
		return defaultAddress;
	}
	/**
	 * 设置：0：未删除 1：已删除
	 */
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	/**
	 * 获取：0：未删除 1：已删除
	 */
	public Integer getDelFlag() {
		return delFlag;
	}
}
