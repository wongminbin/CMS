/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.entity;

import java.util.Date;

/**
 * 网站配置实体
 * 
 * @author zsy
 * 
 */

public class Config {

	/**
	 * key
	 */
	private String key;

	/**
	 * 值
	 */
	private String value;
	/**
	 * 描述
	 */
	private String description;

	/**
	 * 时间
	 */
	private Date createTime;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
