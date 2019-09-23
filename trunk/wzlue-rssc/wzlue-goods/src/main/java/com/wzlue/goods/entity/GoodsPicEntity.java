package com.wzlue.goods.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品图片
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 19:59:39
 */
public class GoodsPicEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//商品id
	private Long goodsId;
	//图片
	private String picUrl;

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
	 * 设置：商品id
	 */
	public void setGoodsId(Long goodsId) {
		this.goodsId = goodsId;
	}
	/**
	 * 获取：商品id
	 */
	public Long getGoodsId() {
		return goodsId;
	}
	/**
	 * 设置：图片
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * 获取：图片
	 */
	public String getPicUrl() {
		return picUrl;
	}
}
