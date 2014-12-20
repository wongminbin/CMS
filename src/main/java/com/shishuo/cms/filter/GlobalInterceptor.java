/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.shishuo.cms.constant.ConfigConstant;
import com.shishuo.cms.service.ConfigService;
import com.shishuo.cms.util.HttpUtils;

/**
 * @author Herbert
 * 
 */
@Component
public class GlobalInterceptor implements HandlerInterceptor {

	@Autowired
	private ConfigService configService;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (null == modelAndView) {
			return;
		}
		// 系统配置参数
		String basePath = HttpUtils.getBasePath(request);
		modelAndView.addObject("BASE_PATH", basePath);
		modelAndView.addObject("UPLOAD_BASE_PATH", basePath + "/upload");
		modelAndView
				.addObject(
						"TEMPLATE_BASE_PATH",
						basePath
								+ "/static/template/"
								+ configService
										.getStringByKey(ConfigConstant.SHISHUO_TEMPLATE));
		modelAndView.addObject("shishuo_seo_title",
				configService.getStringByKey("shishuo_seo_title"));
		modelAndView.addObject("shishuo_seo_description",
				configService.getStringByKey("shishuo_seo_description"));
		MDC.put("ip", HttpUtils.getIp(request));
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
