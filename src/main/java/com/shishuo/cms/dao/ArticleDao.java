/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.constant.ArticleConstant;
import com.shishuo.cms.constant.ArticleConstant.check;
import com.shishuo.cms.entity.Article;
import com.shishuo.cms.entity.vo.ArticleVo;

/**
 * 文件服务
 * 
 * @author Harbored
 * 
 */
@Repository
public interface ArticleDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加文件
	 * 
	 * @return Integer
	 */
	public int addArticle(Article article);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除文件
	 * 
	 * @return boolean
	 */
	public boolean deleteArticleById(@Param("articleId") long articleId);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 修改文件
	 * 
	 * @param article
	 * @return Integer
	 */
	public int updateArticle(Article article);

	/**
	 * 更新浏览人数
	 * 
	 * @param articleId
	 * @param viewCount
	 * @return int
	 */
	public int updateViewCount(@Param("articleId") long articleId,
			@Param("viewCount") int viewCount);

	/**
	 * 更新评论数
	 * 
	 * @param articleId
	 * @param commentCount
	 * @return int
	 */

	public int updateCommentCount(@Param("articleId") long articleId,
			@Param("commentCount") int commentCount);

	public int updateCheck(@Param("articleId") long articleId,
			@Param("check") check check);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 得到文件
	 * 
	 * @param articleId
	 * @return File
	 */
	public ArticleVo getArticleById(@Param("articleId") long articleId);

	/**
	 * 得到目录的文件的列表
	 * 
	 * @param foderId
	 * @return List<FileVo>
	 */
	public List<ArticleVo> getArticleListOfDisplayByPath(
			@Param("path") String path, @Param("offset") long offset,
			@Param("rows") long rows);

	/**
	 * 得到目录的所有文件的数量
	 * 
	 * @param foderId
	 * @return Integer
	 */
	public int getArticleCountOfDisplayByPath(@Param("path") String path);

	/**
	 * 得到某种显示的文件的列表
	 * 
	 * @param foderId
	 * @return List<FileVo>
	 */
	public List<ArticleVo> getArticleListByAdminIdAndPath(
			@Param("adminId") long adminId, @Param("path") String path,
			@Param("check") ArticleConstant.check check,
			@Param("offset") long offset, @Param("rows") long rows);

	/**
	 * @param firstFolderId
	 * @param secondFolderId
	 * @param thirdFolderId
	 * @param fourthFolderId
	 * @return
	 */
	public int getArticleCountByAdminIdAndPath(@Param("adminId") long adminId,
			@Param("path") String path,
			@Param("check") ArticleConstant.check check);

	/**
	 * @param adminId
	 * @param path
	 * @return
	 */
	public int getArticleCountByFolderId(@Param("folderId") long folderId);

}
