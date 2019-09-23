package com.wzlue.member.entity;

import java.io.Serializable;

/**
 * 会员推荐人列表
 *
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-18 16:57:45
 */
public class MemberRecommendEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//主键
	private Long id;
	//会员id
	private Long memberId;
	//会员手机号
	private String memberMobile;
	//会员昵称
	private String memberNickName;
	//推荐人ID
	private Long recommenderId;
	//推荐人手机号
	private String recommenderMobile;
	//推荐人昵称
	private String recommenderNickName;
	//会员身份（1是2否）
	private Integer memberIsVip;

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
	 * 设置：会员手机号
	 */
	public void setMemberMobile(String memberMobile) {
		this.memberMobile = memberMobile;
	}

	/**
	 * 获取：会员手机号
	 */
	public String getMemberMobile() {
		return memberMobile;
	}

	/**
	 * 设置：会员昵称
	 */
	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
	}

	/**
	 * 获取：会员昵称
	 */
	public String getMemberNickName() {
		return memberNickName;
	}

	/**
	 * 设置：推荐人ID
	 */
	public void setRecommenderId(Long recommenderId) {
		this.recommenderId = recommenderId;
	}

	/**
	 * 获取：推荐人ID
	 */
	public Long getRecommenderId() {
		return recommenderId;
	}

	/**
	 * 设置：推荐人手机号
	 */
	public void setRecommenderMobile(String recommenderMobile) {
		this.recommenderMobile = recommenderMobile;
	}

	/**
	 * 获取：推荐人手机号
	 */
	public String getRecommenderMobile() {
		return recommenderMobile;
	}

	/**
	 * 设置：推荐人昵称
	 */
	public void setRecommenderNickName(String recommenderNickName) {
		this.recommenderNickName = recommenderNickName;
	}

	/**
	 * 获取：推荐人昵称
	 */
	public String getRecommenderNickName() {
		return recommenderNickName;
	}

	/**
	 * 设置：会员身份（1是2否）
	 */
	public void setMemberIsVip(Integer memberIsVip) {
		this.memberIsVip = memberIsVip;
	}

	/**
	 * 获取：会员身份（1是2否）
	 */
	public Integer getMemberIsVip() {
		return memberIsVip;
	}
}

