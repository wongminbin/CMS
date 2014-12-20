/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.tag;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shishuo.cms.constant.FolderConstant;
import com.shishuo.cms.entity.Folder;
import com.shishuo.cms.entity.vo.FolderVo;
import com.shishuo.cms.exception.FolderNotFoundException;
import com.shishuo.cms.plugin.TagPlugin;
import com.shishuo.cms.service.FolderService;

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
public class FolderListTag extends TagPlugin {
	@Autowired
	private FolderService folderService;

	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {

		long folderId = 0;
		List<FolderVo> list = new ArrayList<FolderVo>();
		// 获取页面的参数
		try {
			if (params.get("ename") == null) {
				folderId = Long.parseLong(params.get("folderId").toString());
			} else {
				String ename = params.get("ename").toString();
				Folder folder = folderService.getFolderByEname(ename);
				folderId = folder.getFolderId();
			}
			list = folderService.getFolderListByFatherId(folderId,
					FolderConstant.status.display);
		} catch (FolderNotFoundException e) {
			// 丢弃
		}
		env.setVariable("tag_folder_list", DEFAULT_WRAPPER.wrap(list));
		body.render(env.getOut());
	}

}
