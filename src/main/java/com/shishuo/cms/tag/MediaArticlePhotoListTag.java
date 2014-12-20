/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.constant.MediaConstant;
import com.shishuo.cms.entity.Media;
import com.shishuo.cms.plugin.TagPlugin;
import com.shishuo.cms.service.MediaService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * folder标签
 * 
 * @author lqq
 * 
 */
@Service
@SuppressWarnings(value={"deprecation", "rawtypes"})
public class MediaArticlePhotoListTag extends TagPlugin {

	@Autowired
	private MediaService attachmentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		// 获取页面的参数
		long kindId = Long.parseLong(params.get("kindId").toString());
		Integer rows = Integer.parseInt(params.get("rows").toString());

		// 获得目录列表
		List<Media> list = attachmentService.getMediaListByKindAndType(kindId,
				MediaConstant.Kind.article, MediaConstant.Type.photo, rows);
		env.setVariable("tag_attachment_list", DEFAULT_WRAPPER.wrap(list));
		body.render(env.getOut());
	}

}
