/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.constant.MediaConstant;
import com.shishuo.cms.constant.MediaConstant.Kind;
import com.shishuo.cms.constant.MediaConstant.Type;
import com.shishuo.cms.entity.Media;

@Repository
public interface MediaDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * @param att
	 * @return
	 */
	public int addMedia(Media att);

	// ///////////////////////////////
	// ///// 删除 ////////
	// ///////////////////////////////

	/**
	 * @param mediaId
	 * @return
	 */
	public int deleteMedia(@Param("mediaId") long mediaId);

	// ///////////////////////////////
	// ///// 修改////////
	// ///////////////////////////////

	public int updateLinkByMediaId(@Param("mediaId") long mediaId,
			@Param("link") String link);

	public int updateNameByMediaId(@Param("mediaId") long mediaId,
			@Param("name") String name);

	// ///////////////////////////////
	// ///// 查询////////
	// ///////////////////////////////

	/**
	 * @param mediaId
	 * @return
	 */
	public Media getMediaById(@Param("mediaId") long mediaId);

	/**
	 * @param folderId
	 * @return
	 */
	public int getMediaCountByKindId(@Param("kindId") long kindId,
			@Param("kind") MediaConstant.Kind kind);

	/**
	 * @param folderId
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Media> getMediaListByKindId(@Param("kindId") long kindId,
			@Param("kind") MediaConstant.Kind kind,
			@Param("offset") int offset, @Param("rows") int rows);

	/**
	 * @param kindId
	 * @param kind
	 * @param type
	 * @param status
	 * @return
	 */
	public int getMediaCountByKindAndType(@Param("kindId") long kindId,
			@Param("kind") Kind kind, @Param("type") Type type);

	/**
	 * @param kindId
	 * @param kind
	 * @param type
	 * @param status
	 * @param offset
	 * @param rows
	 * @return
	 */
	public List<Media> getMediaListByKindAndType(@Param("kindId") long kindId,
			@Param("kind") Kind kind, @Param("type") Type type,
			@Param("offset") int offset, @Param("rows") int rows);

}
