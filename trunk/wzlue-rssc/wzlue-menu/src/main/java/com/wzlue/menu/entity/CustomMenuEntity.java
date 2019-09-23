package com.wzlue.menu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 自定义菜单
 * 
 * @author wzlue
 * @email wzlue.com
 * @date 2019-07-01 14:17:34
 */
public class CustomMenuEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//
	private Long id;
	//菜单
	private String head;
	//标题
	private String title;
	//正文
	private String body;
	//状态：1显示；2隐藏
	private Integer state;
	//分类：1会员协议；2积分规则；3积分兑换规则；4联系客服；5会员权益；20自定义菜单
	private Integer classification;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getClassification() {
        return classification;
    }

    public void setClassification(Integer classification) {
        this.classification = classification;
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
	 * 设置：菜单
	 */
	public void setHead(String head) {
		this.head = head;
	}
	/**
	 * 获取：菜单
	 */
	public String getHead() {
		return head;
	}
	/**
	 * 设置：标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：标题
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：正文
	 */
	public void setBody(String body) {
		this.body = body;
	}
	/**
	 * 获取：正文
	 */
	public String getBody() {
		return body;
	}
	/**
	 * 设置：1上架；2下架
	 */
	public void setState(Integer state) {
		this.state = state;
	}
	/**
	 * 获取：1上架；2下架
	 */
	public Integer getState() {
		return state;
	}
}
