/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */
package com.shishuo.cms.action.manage;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.constant.FolderConstant;
import com.shishuo.cms.constant.MediaConstant;
import com.shishuo.cms.entity.Admin;
import com.shishuo.cms.entity.Folder;
import com.shishuo.cms.entity.Media;
import com.shishuo.cms.entity.vo.FolderVo;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.FolderNotFoundException;
import com.shishuo.cms.util.RegexUtils;
import com.shishuo.cms.util.SSUtils;

/**
 * @author 目录action
 * 
 */
@RequestMapping("/manage/folder")
@Controller
public class ManageFolderAction extends ManageBaseAction {

	/**
	 * @author 进入添加目录页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	public String add(ModelMap modelMap, HttpServletRequest request)
			throws Exception {
		Admin admin = this.getAdmin(request);
		modelMap.put("folderAll",
				folderService.getAllFolderList(admin.getAdminId()));
		modelMap.put("folderName", "");
		modelMap.put("folderEname", "");
		return "manage/folder/add";
	}

	/**
	 * @author 添加新的目录
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/add.json", method = RequestMethod.POST)
	public JsonVo<String> add(
			@RequestParam(value = "fatherId", defaultValue = "0") long fatherId,
			@RequestParam(value = "folderName") String folderName,
			@RequestParam(value = "folderEname") String folderEname,
			@RequestParam(value = "status") FolderConstant.status status,
			@RequestParam(value = "check") FolderConstant.check check,
			ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(folderName)) {
				json.getErrors().put("folderName", "目录名称不能为空");
			}
			if (StringUtils.isBlank(folderEname)) {
				json.getErrors().put("folderEname", "英文名称不能为空");
			} else if (!RegexUtils.isAlphaUnderline(folderEname)) {
				json.getErrors().put("folderEname", "只能是英文字母，数字和下划线");
			} else if (folderService.isFolderByEname(folderEname)) {
				json.getErrors().put("folderEname", "英文名称不能重复");
			}
			// 检测校验结果
			validate(json);
			folderService.addFolder(fatherId,
					SSUtils.toText(folderName.trim()),
					SSUtils.toText(folderEname.toLowerCase().trim()), status,
					check);
			json.setResult(true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * @author 进入目录列表
	 * @throws FolderNotFoundException
	 * 
	 */
	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	public String list(
			@RequestParam(value = "folderId", defaultValue = "0") long folderId,
			ModelMap modelMap, HttpServletRequest request)
			throws FolderNotFoundException {
		List<FolderVo> pathList = folderService
				.getFolderPathListByFolderId(folderId);
		Folder folder = new Folder();
		if (folderId == 0) {
			folder.setFolderId(0);
			folder.setName("首页");
		} else {
			folder = folderService.getFolderById(folderId);
		}
		Admin admin = this.getAdmin(request);
		List<FolderVo> folderList = folderService.getFolderListByFatherId(
				folderId, null);
		modelMap.put("folder", folder);
		modelMap.put("folderList", folderList);
		modelMap.put("pathList", pathList);
		modelMap.put("folderName", "");
		modelMap.put("folderEname", "");
		modelMap.put("folderAll",
				folderService.getAllFolderList(admin.getAdminId()));
		return "manage/folder/list";
	}

	/**
	 * @author 进入修改目录资料页面
	 * @throws Exception
	 * 
	 */
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	public String oneFolder(@RequestParam("folderId") long folderId,
			@RequestParam(value = "p", defaultValue = "1") int p,
			ModelMap modelMap, HttpServletRequest request) throws Exception {
		Folder folder = folderService.getFolderById(folderId);
		if (folder.getContent() == null) {
			folder.setContent("");
		}
		if (folder.getFatherId() == 0) {
			modelMap.put("fatherFolderName", "未分类");
		} else {
			Folder fatherFolder = folderService.getFolderById(folder
					.getFatherId());
			modelMap.put("fatherFolderName", fatherFolder.getName());
		}
		PageVo<Media> pageVo = attachmentService.getMediaPageByKindId(folderId,
				MediaConstant.Kind.folder, 12, p);
		pageVo.getArgs().put("folderId", folderId + "");
		modelMap.put("folder", folder);
		modelMap.put("folderAll", folderService.getAllFolderList(0));
		modelMap.put("JSESSIONID", request.getSession().getId());
		modelMap.put("attachmentPage", pageVo);
		return "manage/folder/update";
	}

	/**
	 * @author 修改目录资料
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/update.json", method = RequestMethod.POST)
	public JsonVo<String> update(
			@RequestParam(value = "folderId") long folderId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "height") int height,
			@RequestParam(value = "width") int width,
			@RequestParam(value = "ename") String ename,
			@RequestParam(value = "status") FolderConstant.status status,
			@RequestParam(value = "content", required = false) String content) {
		JsonVo<String> json = new JsonVo<String>();
		// FIXME 检查目录的ename不能用循环遍历检查
		List<FolderVo> list = folderService.getAllFolderList(0);
		try {
			if (name.equals("")) {
				json.getErrors().put("name", "目录名称不能为空");
			}
			if (ename.equals("")) {
				json.getErrors().put("ename", "英文名称不能为空");
			} else {
				for (Folder folder : list) {
					if (folderId != folder.getFolderId()) {
						if (ename.equals(folder.getEname())) {
							json.getErrors().put("folderEname", "英文名称不能重复");
						}
					}
				}
			}
			// 检测校验结果
			validate(json);
			folderService.updateFolderById(folderId,
					SSUtils.toText(name.trim()),
					SSUtils.toText(ename.trim().toLowerCase()), status,
					content, height, width);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	/**
	 * @author 目录排序
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/sort.json", method = RequestMethod.POST)
	public JsonVo<String> delete(
			@RequestParam(value = "sortJson") String sortJson) {
		JsonVo<String> json = new JsonVo<String>();
		JSONArray array = JSONArray.fromObject(sortJson);
		for (int i = 0; i < array.size(); i++) {
			JSONObject folder = array.getJSONObject(i);
			String folderId = folder.get("folderId").toString();
			String sort = folder.get("sort").toString();
			folderService.updateSort(Long.parseLong(folderId),
					Integer.parseInt(sort));
		}
		json.setResult(true);
		return json;
	}

	/**
	 * @author 删除目录
	 * @throws FolderNotFoundException
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/delete.json", method = RequestMethod.POST)
	public JsonVo<String> delete(@RequestParam(value = "folderId") long folderId)
			throws FolderNotFoundException {
		JsonVo<String> json = new JsonVo<String>();
		List<FolderVo> folderList = folderService.getFolderListByFatherId(
				folderId, null);
		if (folderList.size() == 0) {
			int count = articleService.getArticleCountByFolderId(folderId);
			if (count != 0) {
				json.setResult(false);
				json.setMsg("此目录下还有文件,不能被删除。");
			} else {
				json.setResult(true);
				folderService.deleteFolderById(folderId);
			}
		} else {
			json.setResult(false);
			json.setMsg("此目录下有子目录，不能删除。");
		}
		return json;
	}
}
