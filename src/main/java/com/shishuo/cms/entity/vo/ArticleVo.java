package com.shishuo.cms.entity.vo;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.shishuo.cms.entity.Article;
import com.shishuo.cms.entity.Folder;

public class ArticleVo extends Article {

	private Folder folder;

	private List<FolderVo> folderPathList;

	public String getPictureUrl() {
		if (StringUtils.isBlank(this.getPicture())) {
			return "upload/blank.jpg";
		} else {
			return this.getPicture();
		}
	}

	public Folder getFolder() {
		return folder;
	}

	public void setFolder(Folder folder) {
		this.folder = folder;
	}

	public List<FolderVo> getFolderPathList() {
		return folderPathList;
	}

	public void setFolderPathList(List<FolderVo> folderPathList) {
		this.folderPathList = folderPathList;
	}
}
