/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */

package com.shishuo.cms.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {

	public static boolean isAlphaUnderline(String msg) {
		Pattern pattern = Pattern.compile("^[a-zA-Z0-9_]+$");
		Matcher matcher = pattern.matcher(msg);
		return matcher.matches();
	}

	public static void main(String[] args) {
		RegexUtils.isAlphaUnderline("ddd3_dd444美丽");
	}
}
