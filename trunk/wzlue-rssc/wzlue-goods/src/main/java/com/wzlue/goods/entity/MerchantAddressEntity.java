package com.wzlue.goods.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商户发货地址
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-09 15:47:43
 */
public class MerchantAddressEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//发货人姓名
	private String name;
	//发货人手机号
	private String phone;
	//省
	private String province;
	//市
	private String city;
	//区
	private String county;
	//街道(详细地址)
	private String street;
	//邮编
	private String zipCode;
	//状态（1可用2禁用）
	private Integer status;

	/**
	 * 城市信息关联字段
	 */
	//省名字
	private String provinceName;
	//市名字
	private String cityName;
	//区名
	private String countyName;

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

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
	 * 设置：发货人姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：发货人姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：发货人手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：发货人手机号
	 */
	public String getPhone() {
		return phone;
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
	 * 设置：街道(详细地址)
	 */
	public void setStreet(String street) {
		this.street = street;
	}
	/**
	 * 获取：街道(详细地址)
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
	 * 设置：状态（1可用2禁用）
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态（1可用2禁用）
	 */
	public Integer getStatus() {
		return status;
	}
}
