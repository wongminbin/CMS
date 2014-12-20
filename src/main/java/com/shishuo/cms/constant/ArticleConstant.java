/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.constant;

/**
 * 文件常量
 * 
 * @author Zhang jiale
 * 
 */
public class ArticleConstant {

	/**
	 * 文件状态
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Status {
		/**
		 * 隐藏
		 */
		hidden, /**
		 * /** 公开的
		 */
		display,
	};

	/**
	 * 审核
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum check {
		/**
		 * 已审核
		 */
		yes, /**
		 * /** 审核失败
		 */
		no, /**
		 * /** 未审核
		 */
		init,
	};
	/**
	 * 是否需要登录
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Login {
		/**
		 * 已审核
		 */
		yes, /**
		 * /** 审核失败
		 */
		no
	};
}
