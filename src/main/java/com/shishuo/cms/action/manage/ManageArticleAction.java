/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.action.manage;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shishuo.cms.action.ArticleAction;
import com.shishuo.cms.constant.ArticleConstant;
import com.shishuo.cms.constant.MediaConstant;
import com.shishuo.cms.entity.Admin;
import com.shishuo.cms.entity.Article;
import com.shishuo.cms.entity.Media;
import com.shishuo.cms.entity.vo.AdminVo;
import com.shishuo.cms.entity.vo.ArticleVo;
import com.shishuo.cms.entity.vo.FolderVo;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.ArticleNotFoundException;
import com.shishuo.cms.exception.FolderNotFoundException;
import com.shishuo.cms.exception.UploadException;
import com.shishuo.cms.util.SSUtils;

/**
 * @author 文件action
 * 
 */
@Controller
@RequestMapping("/manage/article")
public class ManageArticleAction extends ManageBaseAction {

	@Autowired
	private ArticleAction articleAction;

	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String add(HttpServletRequest request, HttpServletResponse response,
			ModelMap modelMap,
			@RequestParam(value = "folderId", defaultValue = "0") long folderId)
			throws FolderNotFoundException {
		Admin admin = this.getAdmin(request);
		modelMap.put("folderAll",
				folderService.getAllFolderList(admin.getAdminId()));
		modelMap.put("folderId", folderId);
		return "manage/article/add";
	}

	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public JsonVo<Article> add(
			@RequestParam("folderId") long folderId,
			@RequestParam("title") String title,
			@RequestParam(value = "summary", required = false) String summary,
			@RequestParam("content") String content,
			@RequestParam(value = "createTime", required = false) String createTime,
			@RequestParam("status") ArticleConstant.Status status,
			@RequestParam("login") ArticleConstant.Login login,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap modelMap)
			throws UploadException, ParseException {
		JsonVo<Article> json = new JsonVo<Article>();
		try {
			Article article = articleService.addArticle(folderId, this
					.getAdmin(request).getAdminId(), SSUtils.toText(title
					.trim()), SSUtils.toText(summary), status,login, content, file,
					createTime);
			json.setT(article);
			json.setResult(true);
			return json;
		} catch (FolderNotFoundException e) {
			e.printStackTrace();
			json.setResult(false);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			json.setResult(false);
			return json;
		}
	}

	/**
	 * @author 进入某种文章的列表分页的首页
	 * @throws FolderNotFoundException
	 * 
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "p", defaultValue = "1") int pageNum,
			@RequestParam(value = "folderId", defaultValue = "0") long folderId,
			@RequestParam(value = "check", required = false) ArticleConstant.check check,
			HttpServletRequest request, ModelMap modelMap)
			throws FolderNotFoundException {
		Admin admin = this.getAdmin(request);
		List<FolderVo> pathList = folderService
				.getFolderPathListByFolderId(folderId);
		PageVo<ArticleVo> pageVo = articleService.getArticlePageByFolderId(
				admin.getAdminId(), folderId, check, pageNum);
		int initCount = articleService.getArticleCountByAdminIdAndFolderId(
				admin.getAdminId(), 0, ArticleConstant.check.init);
		int noCount = articleService.getArticleCountByAdminIdAndFolderId(
				admin.getAdminId(), 0, ArticleConstant.check.no);
		int allCount = initCount
				+ noCount
				+ articleService.getArticleCountByAdminIdAndFolderId(
						admin.getAdminId(), 0, ArticleConstant.check.yes);
		modelMap.put("pathList", pathList);
		modelMap.put("folderId", folderId);
		modelMap.put("pageVo", pageVo);
		modelMap.put("p", pageNum);
		modelMap.put("initCount", initCount);
		modelMap.put("noCount", noCount);
		modelMap.put("allCount", allCount);
		return "manage/article/list";
	}

	/**
	 * @author 进入修改文章页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String update(
			@RequestParam(value = "articleId", defaultValue = "1") long articleId,
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Admin admin = this.getAdmin(request);
		ArticleVo article = articleService.getArticleById(articleId);
		modelMap.put("article", article);
		modelMap.put("folderAll",
				folderService.getAllFolderList(admin.getAdminId()));
		modelMap.put("JSESSIONID", request.getSession().getId());
		return "manage/article/update";
	}

	/**
	 * @author 修改文章资料
	 * @param fileId
	 * @param folderId
	 * @param name
	 * @param titile
	 * @param content
	 * @param description
	 * @param status
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws ParseException
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<Article> update(
			@RequestParam("articleId") long articleId,
			@RequestParam("folderId") long folderId,
			@RequestParam("title") String title,
			@RequestParam("summary") String summary,
			@RequestParam(value = "createTime", required = false) String createTime,
			@RequestParam("content") String content,
			@RequestParam("status") ArticleConstant.Status status,
			@RequestParam("login") ArticleConstant.Login login,
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap modelMap)
			throws ParseException {
		JsonVo<Article> json = new JsonVo<Article>();
		try {
			Article article = articleService.updateArticle(articleId,
					folderId, this.getAdmin(request).getAdminId(),
					SSUtils.toText(title.trim()), SSUtils.toText(summary),
					content, status,login, file, createTime);
			json.setT(article);
			json.setResult(true);
			return json;
		} catch (UploadException e) {
			e.printStackTrace();
			json.setResult(false);
			return json;
		} catch (IOException e) {
			e.printStackTrace();
			json.setResult(false);
			return json;
		} catch (FolderNotFoundException e) {
			e.printStackTrace();
			json.setResult(false);
			return json;
		}
	}

	/**
	 * @author 彻底删除文件
	 * @throws ArticleNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> deleteFile(
			@RequestParam(value = "articleId") long articleId)
			throws ArticleNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		// 删除文件系统
		articleService.deleteArticleById(articleId);
		List<Media> attachmentList = attachmentService.getMediaPageByKindId(
				articleId, MediaConstant.Kind.article, 1000, 1).getList();
		for (Media attachment : attachmentList) {
			attachmentService.deleteMedia(attachment.getMediaId(),
					attachment.getPath());
		}
		json.setResult(true);
		return json;
	}

	/**
	 * @author 修改文件审核状态
	 * @throws ArticleNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/check.json", method = RequestMethod.POST)
	public JsonVo<String> check(
			@RequestParam(value = "articleId") long articleId,
			@RequestParam(value = "check") ArticleConstant.check check,
			HttpServletRequest request) throws ArticleNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		AdminVo admin = this.getAdmin(request);
		if (!admin.getIsAdmin()) {
			json.setResult(false);
			json.setMsg("您不是超级管理员，无权该审核文件！");
		} else {
			articleService.updateCheck(articleId, check);
			json.setResult(true);
		}
		return json;
	}

	// @RequestMapping(value = "/preview.htm", method = RequestMethod.GET)
	// public String preview(@RequestParam(value = "articleId") long articleId,
	// @RequestParam(value = "p", defaultValue = "0") int p,
	// ModelMap modelMap, HttpServletRequest request) {
	// return articleAction.folder(articleId, p, modelMap);
	// }

	@RequestMapping(value = "/preview.htm", method = RequestMethod.GET)
	public String preview(@RequestParam(value = "articleId") long articleId,
			@RequestParam(value = "p", defaultValue = "0") int p,
			ModelMap modelMap, HttpServletRequest request) {
		return articleAction.article(articleId, p, modelMap);
	}
}
