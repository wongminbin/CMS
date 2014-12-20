/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.constant;

/**
 * 评论表的常量
 * 
 * @author Administrator
 * 
 */
public class CommentConstant {
	/**
	 * @author Herbert
	 * 
	 */
	public static enum Status {
		/**
		 * 隐藏
		 */
		hidden,
		/**
		 * 显示
		 */
		display,
		/**
		 * 垃圾
		 */
		trash
	};

	public static enum kind {
		/**
		 * 隐藏
		 */
		article,
		/**
		 * 显示
		 */
		folder
	};
}
