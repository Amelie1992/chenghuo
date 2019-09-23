package com.wzlue.goods.entity;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 商品规格
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2018-07-25 20:42:51
 */
public class SpecEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//主键
	private Long id;
	//规格名称
	private String specName;
	//父id
	private Long parentId;
	//排序
	private Long sort;
	//规格价格
	private BigDecimal specPrice;
	//规格会员价
	private BigDecimal specVipPrice;
	private int stock;
	private String specUnit;
	private String specNo;
    private Long specId;
    private Long specIdTwo;
    private String specNameTwo;
    private String specPic;

	public Long getSpecId() {
		return specId;
	}

	public void setSpecId(Long specId) {
		this.specId = specId;
	}

	public Long getSpecIdTwo() {
		return specIdTwo;
	}

	public void setSpecIdTwo(Long specIdTwo) {
		this.specIdTwo = specIdTwo;
	}

	public String getSpecNameTwo() {
		return specNameTwo;
	}

	public void setSpecNameTwo(String specNameTwo) {
		this.specNameTwo = specNameTwo;
	}

	public String getSpecPic() {
		return specPic;
	}

	public void setSpecPic(String specPic) {
		this.specPic = specPic;
	}

	public String getSpecUnit() {
		return specUnit;
	}

	public void setSpecUnit(String specUnit) {
		this.specUnit = specUnit;
	}

	public String getSpecNo() {
		return specNo;
	}

	public void setSpecNo(String specNo) {
		this.specNo = specNo;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public BigDecimal getSpecPrice() {
		return specPrice;
	}

	public void setSpecPrice(BigDecimal specPrice) {
		this.specPrice = specPrice;
	}

	public BigDecimal getSpecVipPrice() {
		return specVipPrice;
	}

	public void setSpecVipPrice(BigDecimal specVipPrice) {
		this.specVipPrice = specVipPrice;
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
	 * 设置：规格名称
	 */
	public void setSpecName(String specName) {
		this.specName = specName;
	}
	/**
	 * 获取：规格名称
	 */
	public String getSpecName() {
		return specName;
	}
	/**
	 * 设置：父id
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	/**
	 * 获取：父id
	 */
	public Long getParentId() {
		return parentId;
	}
	/**
	 * 设置：排序
	 */
	public void setSort(Long sort) {
		this.sort = sort;
	}
	/**
	 * 获取：排序
	 */
	public Long getSort() {
		return sort;
	}
}
