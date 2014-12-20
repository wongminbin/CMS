/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.action.manage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shishuo.cms.constant.SystemConstant;
import com.shishuo.cms.entity.vo.JsonVo;
import com.shishuo.cms.util.SSUtils;

/**
 * 网站配置action
 * 
 * @author Herbert
 * 
 */
@Controller
@RequestMapping("/manage/config")
public class ManageConfigAction extends ManageBaseAction {

	/**
	 * 网站配置
	 * 
	 * @author Administrator
	 * 
	 */
	@RequestMapping(value = "/basic.htm", method = RequestMethod.GET)
	public String basic(ModelMap modelMap) {
		List<String> templateList = this.getTemplate();
		modelMap.addAttribute("templateList", templateList);
		return "system/config/basic";
	}

	/**
	 * 修改网站配置
	 * 
	 * @author Administrator
	 * 
	 */
	@ResponseBody
	@RequestMapping(value = "/basic.json", method = RequestMethod.POST)
	public JsonVo<String> basicSubmit(
			@RequestParam(value = "sitename") String sitename,
			@RequestParam(value = "sitedesc") String sitedesc, ModelMap modelMap) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(sitename)) {
				json.getErrors().put("sitename", "网站名称不能为空");
			}
			if (StringUtils.isBlank(sitedesc)) {
				json.getErrors().put("sitedesc", "网站描述不能为空");
			}

			// 检测校验结果
			validate(json);
			configService.updagteConfigByKey("sys_sitename",
					SSUtils.toText(sitename));
			configService.updagteConfigByKey("sys_sitedesc",
					SSUtils.toText(sitedesc));
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;

	}

	@RequestMapping(value = "/picture.htm", method = RequestMethod.GET)
	public String picture() {
		return "system/config/picture";
	}

	@ResponseBody
	@RequestMapping(value = "/update/picture.json", method = RequestMethod.GET)
	public JsonVo<String> updatePicture(
			@RequestParam(value = "bigWidth") String bigWidth,
			@RequestParam(value = "bigheight") String bigheight,
			@RequestParam(value = "smallWidth") String smallWidth,
			@RequestParam(value = "smallHeight") String smallHeight) {
		JsonVo<String> json = new JsonVo<String>();
		try {
			if (StringUtils.isBlank(bigWidth)) {
				json.getErrors().put("bigWidth", "大图的宽度不能为空");
			}
			if (StringUtils.isBlank(bigheight)) {
				json.getErrors().put("bigheight", "大图的高度不能为空");
			}
			if (StringUtils.isBlank(smallWidth)) {
				json.getErrors().put("smallWidth", "小图的宽度不能为空");
			}
			if (StringUtils.isBlank(smallHeight)) {
				json.getErrors().put("smallHeight", "小图的高度不能为空");
			}

			// 检测校验结果
			validate(json);
			String strb = bigWidth + "x" + bigheight + ";" + smallWidth + "x"
					+ smallHeight + ";";
			configService.updagteConfigByKey("article_picture_size", strb);
			json.setResult(true);
		} catch (Exception e) {
			json.setResult(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}

	private List<String> getTemplate() {
		List<String> templateList = new ArrayList<String>();
		String templatePath = SystemConstant.SHISHUO_CMS_ROOT + "/WEB-INF/";
		File dir = new File(templatePath);
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory() && !file.getName().equals("admin")) {
				templateList.add(file.getName());
			}
			logger.debug(file.getName());
		}
		return templateList;
	}

}
