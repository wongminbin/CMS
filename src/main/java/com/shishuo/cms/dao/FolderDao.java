/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.constant.FolderConstant;
import com.shishuo.cms.constant.FolderConstant.status;
import com.shishuo.cms.entity.Folder;
import com.shishuo.cms.entity.vo.FolderVo;

/**
 * 目录服务
 * 
 * @author Harbored
 * 
 */

@Repository
public interface FolderDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////
	/**
	 * 增加目录
	 * 
	 * @return Integer
	 */
	public int addFolder(Folder folder);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////
	/**
	 * 删除目录
	 * 
	 * @param folder
	 * @return boolean
	 */
	public boolean deleteFolder(@Param("folderId") long folderId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * @param folderId
	 * @param name
	 * @param ename
	 * @param content
	 * @param status
	 */
	public void updateFolderById(@Param("folderId") long folderId,
			@Param("name") String name, @Param("ename") String ename,
			@Param("status") FolderConstant.status status,
			@Param("content") String content, @Param("height") int height,
			@Param("width") int width);

	public int updateSort(@Param("folderId") long folderId,
			@Param("sort") int sort);

	public int updateCount(@Param("folderId") long folderId,
			@Param("count") int count);

	// ///////////////////////////////
	// ///// 查询 ////////
	// ///////////////////////////////
	/**
	 * 得到目录
	 * 
	 * @param folderId
	 * @return Folder
	 */
	public FolderVo getFolderById(@Param("folderId") long folderId);

	/**
	 * 得到所有子目录
	 * 
	 * @param fatherId
	 * @return List<FolderVo>
	 */
	public List<FolderVo> getFolderListByFatherId(
			@Param("fatherId") long fatherId,
			@Param("status") FolderConstant.status status);

	/**
	 * 通过ename和fatherId获得指定目录
	 * 
	 * @param ename
	 * @param fatherId
	 * @return
	 */
	public Folder getFolderByEname(@Param("ename") String ename);

	/**
	 * @param folderId
	 * @param status
	 */
	public void updateStatus(@Param("folderId") long folderId,
			@Param("status") status status);

	public int updatePath(@Param("folderId") long folderId,
			@Param("path") String path);

	public List<FolderVo> getAllFolderList();

}
