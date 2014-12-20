package com.shishuo.cms.entity;

import java.util.Date;

import com.shishuo.cms.constant.GuestbookConstant;

public class Guestbook {

	private long guestbookId;
	private String name;
	private String email;
	private String website;
	private String title;
	private String content;
	private String reply;
	private GuestbookConstant.status status;
	private Date createTime;
	private Date replyTime;

	public long getGuestbookId() {
		return guestbookId;
	}

	public void setGuestbookId(long guestbookId) {
		this.guestbookId = guestbookId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public GuestbookConstant.status getStatus() {
		return status;
	}

	public void setStatus(GuestbookConstant.status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReplyTime() {
		return replyTime;
	}

	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}

}
