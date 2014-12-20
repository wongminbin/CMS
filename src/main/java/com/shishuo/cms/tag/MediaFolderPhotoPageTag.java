/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.constant.MediaConstant;
import com.shishuo.cms.entity.Media;
import com.shishuo.cms.entity.vo.PageVo;
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
public class MediaFolderPhotoPageTag extends TagPlugin {

	@Autowired
	private MediaService attachmentService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		// 获取页面的参数
		Integer kindId = Integer.parseInt(params.get("kindId").toString());
		Integer pageNum = Integer.parseInt(params.get("p").toString());
		Integer rows = Integer.parseInt(params.get("rows").toString());
		// 获得目录列表
		PageVo<Media> pageVo = attachmentService.getMediaPageByKindAndType(
				kindId, MediaConstant.Kind.folder, MediaConstant.Type.photo,
				rows, pageNum);
		env.setVariable("tag_attachment_page", DEFAULT_WRAPPER.wrap(pageVo));
		body.render(env.getOut());
	}

}
