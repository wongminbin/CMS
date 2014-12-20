/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.constant;

/**
 * 附件
 * 
 * @author Herbert
 * 
 */
public class MediaConstant {

	/**
	 * 类型<br>
	 * photo：照片<br>
	 * file：文件<br>
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Type {
		/**
		 * 相册
		 */
		photo, /**
		 * 文件
		 */
		file, video
	}

	/**
	 * 种类
	 * 
	 * @author Herbert
	 * 
	 */
	public static enum Kind {
		/**
		 * 目录
		 */
		folder, /**
		 * 文章
		 */
		article, /**
		 * 编辑器
		 */
		editor
	}
}
