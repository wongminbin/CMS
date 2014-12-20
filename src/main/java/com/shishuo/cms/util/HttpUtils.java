/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.shishuo.cms.constant.SystemConstant;

public class HttpUtils {

	/**
	 * 得到请求的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (StringUtils.isBlank(ip)) {
			ip = request.getHeader("Host");
		}
		if (StringUtils.isBlank(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isBlank(ip)) {
			ip = "0.0.0.0";
		}
		return ip;
	}

	/**
	 * 得到请求的根目录
	 * 
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + path;
		return basePath;
	}

	/**
	 * 得到结构目录
	 * 
	 * @param request
	 * @return
	 */
	public static String getContextPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return path;
	}

	/**
	 * @return
	 */
	public static String getRealPath() {
		return SystemConstant.SHISHUO_CMS_ROOT;
	}
}
