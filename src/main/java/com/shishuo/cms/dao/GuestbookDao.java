package com.shishuo.cms.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.constant.GuestbookConstant;
import com.shishuo.cms.entity.Guestbook;
import com.shishuo.cms.entity.vo.GuestbookVo;

@Repository
public interface GuestbookDao {

	public int addGuestbook(Guestbook guestbook);

	public int updateReplyById(@Param("reply") String reply,
			@Param("guestbookId") long guestbookId,
			@Param("status") GuestbookConstant.status status,
			@Param("replyTime") Date replyTime);

	public GuestbookVo getGuestbookById(@Param("guestbookId") long guestbookId);

	public List<GuestbookVo> getGuestbookList(
			@Param("status") GuestbookConstant.status status,
			@Param("offset") long offset, @Param("rows") long rows);

	public int getGuestbookCountList(
			@Param("status") GuestbookConstant.status status);

	public int updateStatusById(
			@Param("status") GuestbookConstant.status status,
			@Param("guestbookId") long guestbookId);
}
