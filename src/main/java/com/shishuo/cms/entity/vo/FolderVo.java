package com.shishuo.cms.entity.vo;

import java.util.ArrayList;
import java.util.List;

import com.shishuo.cms.entity.Folder;

public class FolderVo extends Folder {

	private String pathName;
	/**
	 * 子目录
	 */
	private List<FolderVo> folderList = new ArrayList<FolderVo>();

	private String owner;

	public String getPathName() {
		return pathName;
	}

	public void setPathName(String pathName) {
		this.pathName = pathName;
	}

	public List<FolderVo> getFolderList() {
		return folderList;
	}

	public void setFolderList(List<FolderVo> folderList) {
		this.folderList = folderList;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

}
