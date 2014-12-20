/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * 属性工具类
 * 
 * @author Herbert
 * 
 */
public class PropertyUtils extends PropertyPlaceholderConfigurer {

	public static final Logger logger = Logger.getLogger(PropertyUtils.class);

	private static Map<String, String> propertyMap;

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactoryToProcess,
			Properties props) throws BeansException {
		super.processProperties(beanFactoryToProcess, props);
		propertyMap = new HashMap<String, String>();
		for (Object key : props.keySet()) {
			String keyStr = key.toString();
			String value = props.getProperty(keyStr);
			propertyMap.put(keyStr, value);
		}
	}

	public static String getValue(String name) {
		String value = propertyMap.get(name);
		if (StringUtils.isBlank(value)) {
			return "";
		} else {
			return value;
		}
	}

	/**
	 * 不带/
	 * @return
	 */
	public static String getRoot() {
		String rootKey = "shishuo.cms.root";
		String cmsRoot = System.getProperty(rootKey);
		//Enumeration<?> enu = System.getProperties().propertyNames();
		if(cmsRoot.endsWith(java.io.File.separatorChar+"")){
			cmsRoot = cmsRoot.substring(0, cmsRoot.length()-1);
		}
		logger.info(cmsRoot);
		return cmsRoot;
	}
}
