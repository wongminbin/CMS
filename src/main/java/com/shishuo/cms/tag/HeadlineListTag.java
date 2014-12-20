/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.entity.vo.HeadlineVo;
import com.shishuo.cms.plugin.TagPlugin;
import com.shishuo.cms.service.HeadlineService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Herbert
 * 
 */
@Service
@SuppressWarnings(value={"deprecation", "rawtypes"})
public class HeadlineListTag extends TagPlugin {

	@Autowired
	private HeadlineService headlineService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		// 获取文件的分页
		List<HeadlineVo> headlineList = headlineService.getHeadlineList();
		env.setVariable("tag_headline_list", BEANS_WRAPPER.wrap(headlineList));
		body.render(env.getOut());
	}

}
