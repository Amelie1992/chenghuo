package com.wzlue.shop.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商家表
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-03-26 15:44:44
 */
public class ShopEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//用户id
	private Long userId;
	//店铺名称
	private String shopName;
	//电话
	private String tel;
	//店铺logo
	private String logo;
	//省
	private String province;
	//城市
	private String city;
	//区
	private String county;
	//详细地址
	private String street;
	//店铺描述
	private String description;
	//合作伙伴
	private String cooperation;
	//
	private Date createTime;
	//0：未删除 1：已删除
	private Integer delFlag;

	public String getCooperation() {
		return cooperation;
	}

	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}

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
	 * 设置：用户id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：用户id
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：店铺名称
	 */
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	/**
	 * 获取：店铺名称
	 */
	public String getShopName() {
		return shopName;
	}
	/**
	 * 设置：电话
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * 获取：电话
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * 设置：店铺logo
	 */
	public void setLogo(String logo) {
		this.logo = logo;
	}
	/**
	 * 获取：店铺logo
	 */
	public String getLogo() {
		return logo;
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
	 * 设置：城市
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * 获取：城市
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
	 * 设置：详细地址
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：详细地址
	 */
	public String getStreet() {
		return street;
	}
	/**
	 * 设置：店铺描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取：店铺描述
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * 设置：
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：
	 */
	public Date getCreateTime() {
		return createTime;
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
