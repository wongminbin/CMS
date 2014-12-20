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

import com.shishuo.cms.constant.GuestbookConstant;
import com.shishuo.cms.entity.vo.GuestbookVo;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.plugin.TagPlugin;
import com.shishuo.cms.service.GuestbookService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Service
@SuppressWarnings(value={"deprecation", "rawtypes"})
public class FeedbackPageTag extends TagPlugin {

	@Autowired
	private GuestbookService messageBoardService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		Integer p = Integer.parseInt(params.get("p").toString());
		PageVo<GuestbookVo> pageVo = messageBoardService.getMessageBoardPage(p,
				GuestbookConstant.status.display, "number");
		env.setVariable("tag_feedback_page", BEANS_WRAPPER.wrap(pageVo));

		body.render(env.getOut());
	}
}
