/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.tag;

import static freemarker.template.ObjectWrapper.BEANS_WRAPPER;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.plugin.TagPlugin;
import com.shishuo.cms.service.ConfigService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * @author Administrator file标签
 */
@Service
@SuppressWarnings(value={"deprecation", "rawtypes"})
public class ConfigTag extends TagPlugin {

	@Autowired
	private ConfigService configService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		String key = params.get("key").toString();
		String value = configService.getStringByKey(key);
		env.setVariable("tag_value", BEANS_WRAPPER.wrap(value));
		body.render(env.getOut());
	}
}
