/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.shishuo.cms.entity.Config;

/**
 * 网站配置
 * 
 * @author Zhangjiale
 * 
 */

@Repository
public interface ConfigDao {

	// ///////////////////////////////
	// ///// 增加 ////////
	// ///////////////////////////////

	/**
	 * 增加配置
	 * 
	 * @return Integer
	 */
	public int addConfig(Config config);

	// ///////////////////////////////
	// ///// 刪除 ////////
	// ///////////////////////////////

	/**
	 * 删除配置
	 * 
	 * return Integer
	 */
	public int deleteConfig(@Param("key") String key);

	// ///////////////////////////////
	// ///// 修改 ////////
	// ///////////////////////////////

	/**
	 * 更新配置
	 * 
	 * @return Integer
	 */
	public int updateConfig(Config config);

	// ///////////////////////////////
	// ///// 查詢 ////////
	// ///////////////////////////////

	/**
	 * 查看配置
	 * 
	 * @return Config
	 */
	public Config getConfigByKey(@Param("key") String key);
}
