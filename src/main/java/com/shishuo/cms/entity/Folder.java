/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.entity;

import java.util.Date;

import com.shishuo.cms.constant.FolderConstant;

/**
 * 目录实体
 * 
 * @author zsy
 * 
 */
public class Folder {
	/**
	 * 目录Id
	 */
	private long folderId;

	/**
	 * 父亲Id
	 */
	private long fatherId;

	/**
	 * 英文名称
	 */
	private String ename;

	/**
	 * 目录名称
	 */
	private String name;

	/**
	 * 内容
	 */
	private String content;

	/**
	 * 路径
	 */
	private String path;
	/**
	 * 层级
	 */
	private int level;

	/**
	 * 排序
	 */
	private int sort;
	/**
	 * 宽
	 */
	private int width;
	/**
	 * 高
	 */
	private int height;

	/**
	 * 文件数
	 */
	private int count;

	/**
	 * 状态
	 */
	private FolderConstant.status status;

	/**
	 * 审核
	 */
	private FolderConstant.check check;

	/**
	 * 创建时间
	 */
	private Date createTime;

	public long getFolderId() {
		return folderId;
	}

	public void setFolderId(long folderId) {
		this.folderId = folderId;
	}

	public long getFatherId() {
		return fatherId;
	}

	public void setFatherId(long fatherId) {
		this.fatherId = fatherId;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public FolderConstant.status getStatus() {
		return status;
	}

	public void setStatus(FolderConstant.status status) {
		this.status = status;
	}

	public FolderConstant.check getCheck() {
		return check;
	}

	public void setCheck(FolderConstant.check check) {
		this.check = check;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
