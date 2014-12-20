package com.shishuo.cms.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shishuo.cms.dao.HeadlineDao;
import com.shishuo.cms.entity.Headline;
import com.shishuo.cms.entity.vo.HeadlineVo;
import com.shishuo.cms.exception.UploadException;
import com.shishuo.cms.util.MediaUtils;

@Service
public class HeadlineService {

	@Autowired
	private HeadlineDao headlineDao;

	@Autowired
	private ConfigService configService;

	public Headline addHeadline(MultipartFile multipartFile, String name,
			String url) throws UploadException, IOException {
		Headline headline = new Headline();
		String picture = MediaUtils.saveImage(multipartFile,
				configService.getIntKey("shishuo_headline_image_width"),
				configService.getIntKey("shishuo_headline_image_height"));
		headline.setName(name);
		headline.setPicture(picture);
		headline.setUrl(url);
		headline.setSort(0);
		headline.setCreateTime(new Date());
		headlineDao.addHeadline(headline);
		return headline;
	}

	public List<HeadlineVo> getHeadlineList() {
		return headlineDao.getHeadlineList();
	}

	public int updateHeadlineById(long headlineId, String name,
			MultipartFile file, String url) throws UploadException, IOException {
		String picture = this.getHeadlineById(headlineId).getPicture();
		if (file != null && !file.isEmpty()) {
			picture = MediaUtils.saveImage(file,
					configService.getIntKey("shishuo_headline_image_width"),
					configService.getIntKey("shishuo_headline_image_height"));
		}
		return headlineDao.updateHeadlineById(headlineId, name, picture, url);
	}

	public HeadlineVo getHeadlineById(long headlineId) {
		return headlineDao.getHeadlineById(headlineId);
	}

	public void deleteHeadline(long headlineId, String pictureUrl) {
		headlineDao.deleteHeadline(headlineId);
		MediaUtils.deleteFile(pictureUrl);
	}

	public void updateSortById(long headlineId, int sort) {
		headlineDao.updateSortById(headlineId, sort);
	}
}
