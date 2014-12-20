/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.shishuo.cms.constant.SystemConstant;

/**
 * @author Herbert
 * 
 */
public class MediaUtils {
	
	private static Logger logger = Logger.getLogger(MediaUtils.class);

	/**
	 * 文件允许格式
	 */
	public static String[] FILE_TYPE = { ".rar", ".doc", ".docx", ".zip",
			".pdf", ".txt", ".swf", ".wmv" };

	/**
	 * 图片允许格式
	 */
	public static String[] PHOTO_TYPE = { ".gif", ".png", ".jpg", ".jpeg",
			".bmp" };

	public static boolean isFileType(String fileName, String[] typeArray) {
		for (String type : typeArray) {
			if (fileName.toLowerCase().endsWith(type)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 递归获得目录的所有地址
	 * 
	 * @param realpath
	 * @param files
	 * @param fileType
	 * @return
	 */
	public static List<java.io.File> getFiles(String realpath,
			List<File> files, String[] fileType) {
		File realFile = new File(realpath);
		if (realFile.isDirectory()) {
			File[] subfiles = realFile.listFiles();
			for (File file : subfiles) {
				if (file.isDirectory()) {
					getFiles(file.getAbsolutePath(), files, fileType);
				} else {
					if (isFileType(file.getName(), fileType)) {
						files.add(file);
					}
				}
			}
		}
		return files;
	}

	/**
	 * 得到文件上传的相对路径
	 * 
	 * @param fileName
	 *            文件名
	 * @return
	 */
	public static String getPath(String fileName) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String uploadPath = "upload/" + formater.format(new Date()) + "/"
				+ UUID.randomUUID().toString().replaceAll("-", "")
				+ getFileExt(fileName);
		return uploadPath;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	public static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 删除物理文件
	 * 
	 * @param path
	 */
	public static void deleteFile(String path) {
		File file = new File(SystemConstant.SHISHUO_CMS_ROOT + path);
		file.delete();
	}

	/**
	 * 保存，并裁剪图片
	 * 
	 * @param multipartFile
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static String saveImage(MultipartFile multipartFile, int width,
			int height) throws IOException {
		logger.info("压缩图片尺寸："+width+" x "+height);
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String path = "upload/" + formater.format(new Date()) + "/"
				+ UUID.randomUUID().toString().replaceAll("-", "") + ".jpg";
		String filePath = SystemConstant.SHISHUO_CMS_ROOT + "/" + path;
		File file = new File(SystemConstant.SHISHUO_CMS_ROOT + "/" + path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		if (width > 0 && height > 0) {
			BufferedImage bufferedImage = ImageIO.read(multipartFile
					.getInputStream());
			int imageWidth = bufferedImage.getWidth();
			int imageHeitht = bufferedImage.getHeight();
			BufferedImage image = null;
			if (width / height < imageWidth / imageHeitht) {
				image = Thumbnails.of(multipartFile.getInputStream())
						.height(height).asBufferedImage();
			} else {
				image = Thumbnails.of(multipartFile.getInputStream())
						.width(width).asBufferedImage();
			}
			Thumbnails.of(image).sourceRegion(Positions.CENTER, width, height)
					.size(width, height).outputFormat("jpg").toFile(filePath);

		} else {
			if (width == 0 && height == 0) {
				multipartFile.transferTo(file);
			} else {
				if (width > 0) {
					Thumbnails.of(multipartFile.getInputStream()).width(width)
							.keepAspectRatio(true).outputFormat("jpg")
							.toFile(filePath);
				}
				if (height > 0) {
					Thumbnails.of(multipartFile.getInputStream())
							.height(height).keepAspectRatio(true)
							.outputFormat("jpg").toFile(filePath);
				}
			}
		}
		// FileOutputStream fos = new FileOutputStream(file);
		// baos.writeTo(fos);
		return path;
	}

	/**
	 * @param multipartFile
	 * @return
	 */
	public static String save(MultipartFile multipartFile) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyy/MM/dd");
		String path = "upload/" + formater.format(new Date()) + "/"
				+ UUID.randomUUID().toString().replaceAll("-", "")
				+ getFileExt(multipartFile.getOriginalFilename());
		File file = new File(SystemConstant.SHISHUO_CMS_ROOT + "/" + path);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}
		return path;
	}

}
