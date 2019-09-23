package com.wzlue.goods.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * 商品标签
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-30 15:03:21
 */
public class GoodsTagEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//商品id
	private Long goodsId;
	//标签id
	private Long tagId;

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
	 * 设置：标签id
	 */
	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}
	/**
	 * 获取：标签id
	 */
	public Long getTagId() {
		return tagId;
	}
}
