/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shishuo.cms.exception.TemplateNotFoundException;

/**
 * 首页
 * 
 * @author Herbert
 */
@Controller
public class IndexAction extends BaseAction {

	/**
	 * 首页
	 * 
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defalut(
			@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		return home(p, modelMap);
	}

	/**
	 * 首页
	 * 
	 * @param pageNum
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String home(@RequestParam(value = "p", defaultValue = "1") long p,
			ModelMap modelMap) {
		try {
			modelMap.addAttribute("p", p);
			modelMap.addAttribute("g_folderId", 0);
			return themeService.getDefaultTemplate();
		} catch (TemplateNotFoundException e) {
			modelMap.addAttribute("g_folderId", 0);
			logger.fatal(e.getMessage());
			return themeService.get404();
		}
	}

	/**
	 * 404
	 * 
	 * @return
	 */
	@RequestMapping(value = "/404.htm", method = RequestMethod.GET)
	public String pageNotFound(ModelMap modelMap) {
		modelMap.addAttribute("g_folderId", 0);
		return themeService.get404();
	}

	/**
	 * 500
	 * 
	 * @return
	 */
	@RequestMapping(value = "/500.htm", method = RequestMethod.GET)
	public String error(ModelMap modelMap) {
		modelMap.addAttribute("g_folderId", 0);
		return themeService.get500();
	}
}
