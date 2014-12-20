/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shishuo.cms.constant.MediaConstant;
import com.shishuo.cms.dao.MediaDao;
import com.shishuo.cms.entity.Media;
import com.shishuo.cms.entity.vo.PageVo;
import com.shishuo.cms.exception.UploadException;
import com.shishuo.cms.util.MediaUtils;

@Service
public class MediaService {

	@Autowired
	private MediaDao mediaDao;

	/*
	 * 上传附件
	 */
	@CacheEvict(value = "media", allEntries = true)
	public Media addUploadFile(MultipartFile multipartFile, String fileName,
			long kindId, MediaConstant.Kind kind) throws IllegalStateException,
			IOException, UploadException {
		MediaConstant.Type type = MediaConstant.Type.photo;
		if (MediaUtils.isFileType(fileName, MediaUtils.PHOTO_TYPE)) {
			type = MediaConstant.Type.photo;
		} else if (MediaUtils.isFileType(fileName, MediaUtils.FILE_TYPE)) {
			type = MediaConstant.Type.file;
		} else {
			throw new UploadException("文件类型有误");
		}
		Date now = new Date();
		String path = MediaUtils.save(multipartFile);
		Media attachment = new Media();
		attachment.setKindId(kindId);
		attachment.setName(fileName);
		attachment.setPath(path);
		attachment.setType(type);
		attachment.setSize(multipartFile.getSize());
		attachment.setKind(kind);
		attachment.setCreateTime(now);
		mediaDao.addMedia(attachment);
		return attachment;
	}

	/**
	 * 
	 * 删除附件通过附件ID
	 */
	@CacheEvict(value = "media", allEntries = true)
	public void deleteMedia(long attachmentId, String path) {
		mediaDao.deleteMedia(attachmentId);
		MediaUtils.deleteFile(path);
	}

	/**
	 * @param attachmentId
	 * @return
	 */
	@Cacheable("media")
	public Media getMediaById(long attachmentId) {
		return mediaDao.getMediaById(attachmentId);
	}

	/**
	 * @param attachmentId
	 * @param link
	 * @return
	 */
	@CacheEvict(value = "media", allEntries = true)
	public int updateLinkByMediaId(long attachmentId, String link) {
		return mediaDao.updateLinkByMediaId(attachmentId, link);
	}

	@CacheEvict(value = "media", allEntries = true)
	public int updateNameByMediaId(long attachmentId, String name) {
		return mediaDao.updateNameByMediaId(attachmentId, name);

	}

	/**
	 * @param folderId
	 * @return
	 */
	@Cacheable("media")
	public PageVo<Media> getMediaPageByKindId(long kindId,
			MediaConstant.Kind kind, int rows, int pageNum) {
		PageVo<Media> pageVo = new PageVo<Media>(pageNum);
		pageVo.setRows(rows);
		pageVo.setCount(mediaDao.getMediaCountByKindId(kindId, kind));
		pageVo.setList(mediaDao.getMediaListByKindId(kindId, kind,
				pageVo.getOffset(), pageVo.getRows()));
		return pageVo;
	}

	/**
	 * @param folderId
	 * @return
	 */
	@Cacheable("media")
	public PageVo<Media> getMediaPageByKindAndType(long kindId,
			MediaConstant.Kind kind, MediaConstant.Type type, int rows,
			int pageNum) {
		PageVo<Media> pageVo = new PageVo<Media>(pageNum);
		pageVo.setRows(rows);
		pageVo.setCount(mediaDao.getMediaCountByKindAndType(kindId, kind, type));
		pageVo.setList(mediaDao.getMediaListByKindAndType(kindId, kind, type,
				pageVo.getOffset(), pageVo.getRows()));
		return pageVo;
	}

	/**
	 * @param kindId
	 * @param kind
	 * @param stauts
	 * @return
	 */
	@Cacheable("media")
	public List<Media> getMediaListByKindId(long kindId,
			MediaConstant.Kind kind, int rows) {
		return mediaDao.getMediaListByKindId(kindId, kind, 0, rows);

	}

	/**
	 * @param kindId
	 * @param kind
	 * @param stauts
	 * @return
	 */
	@Cacheable("media")
	public List<Media> getMediaListByKindAndType(long kindId,
			MediaConstant.Kind kind, MediaConstant.Type type, int rows) {
		return mediaDao.getMediaListByKindAndType(kindId, kind, type, 0, rows);

	}

}
