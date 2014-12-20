/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.entity;

import java.util.Date;

import com.shishuo.cms.constant.MediaConstant;

//TODO media
public class Media {

	private long mediaId;
	private long kindId;
	private String name;
	private String path;
	private long size;
	private MediaConstant.Type type;
	private MediaConstant.Kind kind;
	private Date createTime;

	public long getMediaId() {
		return mediaId;
	}

	public void setMediaId(long mediaId) {
		this.mediaId = mediaId;
	}

	public long getKindId() {
		return kindId;
	}

	public void setKindId(long kindId) {
		this.kindId = kindId;
	}

	public MediaConstant.Kind getKind() {
		return kind;
	}

	public void setKind(MediaConstant.Kind kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public MediaConstant.Type getType() {
		return type;
	}

	public void setType(MediaConstant.Type type) {
		this.type = type;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
