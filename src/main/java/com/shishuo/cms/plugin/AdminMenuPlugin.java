package com.shishuo.cms.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shishuo.cms.entity.vo.MenuVo;

public abstract class AdminMenuPlugin implements Plugin {
	public enum MenuGroup {
		tools
	}

	public static Map<String, List<MenuVo>> adminMenuList = new HashMap<String, List<MenuVo>>();

	/**
	 * 增加菜单
	 * 
	 * @param menuGroup
	 * @param name
	 * @param url
	 */
	public void addMenu(MenuGroup menuGroup, String name, String url) {
		adminMenuList.get(menuGroup.name()).add(new MenuVo(name, url));
	}
}
