/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shishuo.cms.constant.FolderConstant;
import com.shishuo.cms.dao.FolderDao;
import com.shishuo.cms.entity.Folder;
import com.shishuo.cms.entity.vo.AdminFolderVo;
import com.shishuo.cms.entity.vo.FolderVo;
import com.shishuo.cms.exception.FolderNotFoundException;

/**
 * 目录服务
 * 
 * @author Zhangjiale
 * 
 */
@Service
public class FolderService {

	protected final Logger logger = Logger.getLogger(this.getClass());

	@Autowired
	private FolderDao folderDao;

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AdminFolderService adminFolderService;

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加目录
	 * 
	 * @param fatherId
	 * @param name
	 * @param ename
	 * @param status
	 * @param type
	 * @return Folder
	 * @throws FolderNotFoundException
	 */
	@CacheEvict(value = "folder", allEntries = true)
	@Transactional
	public Folder addFolder(long fatherId, String name, String ename,
			FolderConstant.status status, FolderConstant.check check)
			throws FolderNotFoundException {
		Folder folder = new Folder();
		folder.setFatherId(fatherId);
		if (fatherId == 0) {
			folder.setLevel(1);
		} else {
			Folder fatherFolder = this.getFolderById(fatherId);
			folder.setLevel(fatherFolder.getLevel() + 1);
		}
		folder.setName(name);
		folder.setEname(ename);
		folder.setContent("");
		folder.setPath("");
		folder.setCount(0);
		folder.setSort(1);
		folder.setStatus(status);
		folder.setCheck(check);
		folder.setCreateTime(new Date());
		folderDao.addFolder(folder);
		adminFolderService.addAdminFolder(adminService.getSuperAdminId(), folder.getFolderId());
		if (fatherId == 0) {
			this.updatePath(folder.getFolderId(), folder.getFolderId() + "");
		} else {
			Folder fatherFolder = this.getFolderById(fatherId);
			this.updatePath(folder.getFolderId(), fatherFolder.getPath() + "#"
					+ folder.getFolderId());
		}
		return folder;
	}

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除目录
	 * 
	 * @param folderId
	 * @return boolean
	 */
	@CacheEvict(value = "folder", allEntries = true)
	public boolean deleteFolderById(long folderId) {
		return folderDao.deleteFolder(folderId);
	}

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 更新目录
	 * 
	 * @param folderId
	 * @param fatherId
	 * @param ename
	 * @param name
	 * @param status
	 * @param type
	 * @param sort
	 * @return folder
	 */
	@CacheEvict(value = "folder", allEntries = true)
	public void updateFolderById(long folderId, String name, String ename,
			FolderConstant.status status, String content, int height, int width) {
		folderDao.updateFolderById(folderId, name, ename, status, content,
				height, width);
	}

	/**
	 * 通过指定Id修改其目录的序列
	 * 
	 * @param folderId
	 * @param sort
	 * @return Integer
	 */
	@CacheEvict(value = "folder", allEntries = true)
	public int updateSort(long folderId, int sort) {
		return folderDao.updateSort(folderId, sort);
	}

	/**
	 * 通过指定Id修改其目录的路径
	 * 
	 * @param folderId
	 * @param path
	 * @return Integer
	 */
	public int updatePath(long folderId, String path) {
		return folderDao.updatePath(folderId, path);
	}

	/**
	 * @param folderId
	 * @param count
	 * @return
	 */
	public int updateCount(long folderId, int count) {
		return folderDao.updateCount(folderId, count);
	}

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 得到指定目录
	 * 
	 * @param folderId
	 * @return Folder
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public FolderVo getFolderById(long folderId) throws FolderNotFoundException {
		FolderVo folder = folderDao.getFolderById(folderId);
		if (folder == null) {
			throw new FolderNotFoundException("");
		} else {
			logger.debug("目录("+folderId+")中的图片尺寸："+folder.getWidth()+" x "+folder.getHeight());
			return folder;
		}
	}

	/**
	 * 得到所有的四层目录
	 * 
	 * @return
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public List<FolderVo> getAllFolderList(long adminId) {
		List<FolderVo> folderList = folderDao.getAllFolderList();
		HashMap<String, FolderVo> folderMap = new HashMap<String, FolderVo>();
		for (FolderVo folder : folderList) {
			folderMap.put(folder.getFolderId() + "", folder);
		}
		for (FolderVo folder : folderList) {
			folderMap.put(folder.getFolderId() + "", folder);
			AdminFolderVo adminFolder = adminFolderService.getAdminFolderById(
					adminId, folder.getFolderId());
			if (adminFolder == null) {
				folder.setOwner("no");
			} else {
				folder.setOwner("yes");
			}
		}
		for (FolderVo folder : folderList) {
			folder.setPathName(getPathName(folderMap, folder.getPath()));
		}
		return folderList;
	}

	@Cacheable(value = "folder")
	private String getPathName(HashMap<String, FolderVo> folderMap, String path) {
		List<String> names = new ArrayList<String>();
		try {
			String[] folderIds = path.split("#");
			for (String folderId : folderIds) {
				names.add(folderMap.get(folderId).getName());
			}
		} catch (NullPointerException e) {
			logger.fatal(path + " - " + StringUtils.join(path.split("#"), ","));
		}
		return StringUtils.join(names, " - ");
	}

	/**
	 * 得到所有子目录
	 * 
	 * @param fatherId
	 * @return List<Folder>
	 */
	@Cacheable(value = "folder")
	public List<FolderVo> getFolderListByFatherId(long fatherId,
			FolderConstant.status status) {
		return folderDao.getFolderListByFatherId(fatherId, status);
	}

	/**
	 * 通过ename和fatherId获得目录
	 * 
	 * @param ename
	 * @param fatherId
	 * @return
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public Folder getFolderByEname(String ename) throws FolderNotFoundException {
		Folder folder = folderDao.getFolderByEname(ename);
		if (folder == null) {
			throw new FolderNotFoundException(ename + " 目录，不存在");
		} else {
			return folder;
		}
	}

	@Cacheable(value = "folder")
	public boolean isFolderByEname(String ename) {
		Folder folder = folderDao.getFolderByEname(ename);
		if (folder == null) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 得到目录的Path
	 * 
	 * @param folderId
	 * @return
	 * @throws FolderNotFoundException
	 */
	@Cacheable(value = "folder")
	public List<FolderVo> getFolderPathListByFolderId(long folderId)
			throws FolderNotFoundException {
		List<FolderVo> list = new ArrayList<FolderVo>();
		if (folderId == 0) {
			return list;
		} else {
			Folder folder = this.getFolderById(folderId);
			String[] str = folder.getPath().split("#");
			for (int i = 0; i < folder.getLevel(); i++) {
				FolderVo fold = this.getFolderById(Long.parseLong(str[i]));
				list.add(fold);
			}
			return list;
		}
	}

	@CacheEvict(value = "folder", allEntries = true)
	public void updateStatus(long folderId, FolderConstant.status status) {
		folderDao.updateStatus(folderId, status);
	}

	/**
	 * 判断是不是已经存在的目录英文名
	 * 
	 * @param ename
	 * @return
	 */
	@Cacheable(value = "folder")
	public boolean isFolderEname(String ename) {
		try {
			this.getFolderByEname(ename);
			return false;
		} catch (FolderNotFoundException e) {
			return true;
		}

	}

	public long firstFolderId(long folderId) {
		FolderVo folder = folderDao.getFolderById(folderId);
		String[] folderIdList = folder.getPath().split("#");
		return Long.parseLong(folderIdList[0]);
	}

}
