/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.entity;

import java.util.Date;

import com.shishuo.cms.constant.ArticleConstant;

/**
 * 文件实体
 * 
 * @author zsy
 * 
 */

public class Article {

	/**
	 * 文件Id
	 */
	private long articleId;

	/**
	 * 所属目录的第一级Id
	 */
	private long folderId;

	/**
	 * 
	 */
	private String path;

	/**
	 * 管理员Id
	 */
	private long adminId;

	/**
	 * 文件名称
	 */
	private String title;

	/**
	 * 文件名称
	 */
	private String summary;

	/**
	 * 文件内容
	 */
	private String content;

	/**
	 * 封面
	 */
	private String picture;

	/**
	 * 浏览人数
	 */
	private int viewCount;

	/**
	 * 评论人数
	 */
	private int commentCount;

	/**
	 * 文件状态
	 */
	private ArticleConstant.Status status;

	/**
	 * 审核
	 */
	private ArticleConstant.check check;
	
	/**
	 * 审核
	 */
	private ArticleConstant.Login login;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;

	public long getArticleId() {
		return articleId;
	}

	public void setArticleId(long articleId) {
		this.articleId = articleId;
	}

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public int getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(int commentCount) {
		this.commentCount = commentCount;
	}

	public ArticleConstant.Status getStatus() {
		return status;
	}

	public void setStatus(ArticleConstant.Status status) {
		this.status = status;
	}

	public ArticleConstant.check getCheck() {
		return check;
	}

	public void setCheck(ArticleConstant.check check) {
		this.check = check;
	}

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

	public ArticleConstant.Login getLogin() {
		return login;
	}

	public void setLogin(ArticleConstant.Login login) {
		this.login = login;
	}
}
