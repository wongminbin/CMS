package com.shishuo.cms.entity;

import java.util.Date;

public class Headline {

	private long headlineId;
	private String name;
	private String picture;
	private String url;
	private int sort;
	private Date createTime;

	public long getHeadlineId() {
		return headlineId;
	}

	public void setHeadlineId(long headlineId) {
		this.headlineId = headlineId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
