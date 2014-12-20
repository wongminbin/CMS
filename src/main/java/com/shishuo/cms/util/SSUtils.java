package com.shishuo.cms.util;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.springframework.web.util.HtmlUtils;

/**
 * @author Herbert
 * 
 */
public class SSUtils {

	/**
	 * 把骆驼命名法的变量，变为大写字母变小写且之前加下划线
	 * 
	 * @param str
	 * @return
	 */
	public static String toUnderline(String str) {
		str = StringUtils.uncapitalize(str);
		char[] letters = str.toCharArray();
		StringBuilder sb = new StringBuilder();
		for (char letter : letters) {
			if (Character.isUpperCase(letter)) {
				sb.append("_" + letter + "");
			} else {
				sb.append(letter + "");
			}
		}
		return StringUtils.lowerCase(sb.toString());
	}

	public static String toText(String str) {
		return HtmlUtils.htmlEscape(str);
	}
	
	public static String toHTML(String str) {
		return Jsoup.clean(str, Whitelist.relaxed());
	}

	public static void main(String[] args) {
		String text = "美丽<script>;alert(123);</script>";
		System.out.println(SSUtils.toText(text));
	}
}
