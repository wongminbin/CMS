/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */
package com.shishuo.cms.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shishuo.cms.entity.Folder;
import com.shishuo.cms.entity.vo.FolderVo;

/**
 * @author Herbert
 * 
 */
@Controller
@RequestMapping("/folder")
public class FolderAction extends BaseAction {

	/**
	 * 目录ID
	 * 
	 * @param ename
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/{folderId}.htm", method = RequestMethod.GET)
	public String folder(@PathVariable long folderId,
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			Folder folder = folderService.getFolderById(folderId);
			modelMap.put("folder", folder);
			FolderVo fatherFolder = folderService.getFolderById(folderService.firstFolderId(folderId));
			modelMap.put("g_folderId", fatherFolder.getFolderId());
			modelMap.put("p", p);
			return themeService.getFolderTemplate(folder.getFolderId());
		} catch (Exception e) {
			modelMap.addAttribute("g_folderId", 0);
			logger.fatal(e.getMessage());
			return themeService.get404();
		}
	}
}
