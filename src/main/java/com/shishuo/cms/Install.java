/*
 *	Copyright © 2013 Changsha Shishuo Network Technology Co., Ltd. All rights reserved.
 *	长沙市师说网络科技有限公司 版权所有
 *	http://www.shishuo.com
 */
package com.shishuo.cms;

import java.io.BufferedInputStream;
import java.io.Console;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.apache.ibatis.jdbc.ScriptRunner;

import com.mysql.jdbc.Connection;

/**
 * 
 * 师说CMS安装程序 如果出现乱码请在控制台运行 <br>
 * Windows: set MAVEN_OPTS=-Dfile.encoding=UTF-8<br>
 * Linux: export MAVEN_OPTS=-Dfile.encoding=UTF-8
 * 
 * @author Herbert
 * 
 */
public class Install {
	private static String CMS_PROPERTIES = "shishuocms.properties";
	private static String CMS_INSTALL_SQL = "sql/install.sql";

	Console console = System.console();

	public static void main(String[] args) {

		Install install = new Install();
		install.welcome();
		for (int i = 1; i <= 10; i++) {
			if (install.importData()) {
				System.out.println("\n\n安装成功，使用 mvn jetty:run 运行系统。\n\n");
				break;
			} else {
				System.out.println("第" + i + "/10 安装失败，请根据错误提示检测 "
						+ CMS_PROPERTIES + " 相关数据库的配置是否正常。");
			}
		}
	}

	/**
	 * 
	 */
	private void welcome() {
		System.out
				.println("                                                                                ");
		System.out
				.println("          7                                                                     ");
		System.out
				.println("          ,,       7,,,,,,,,,,,,,,,,,,    ,=,,,,,,,      ,, 7       ,,,         ");
		System.out
				.println("    ,,   ,,,7  ,,,,,,,,,,,,,,,,,,,,,, 7  ,,,,,,,,,,,,  I,,,,,      ,,,,         ");
		System.out
				.println("  ,,,,,  ,,,  ?,,,,,,,,=II?,,,,,,,,,    7,,,,,,,,,,, 7   ,,,,     ,,,,          ");
		System.out
				.println("  ,,,,,   ,,   =+      7,,7                7,,,I         7,,,  7?,,+    7       ");
		System.out
				.println("  :,,,,   ,,        7  ,,,,                             I ,,,,,,,,,,,,,,,       ");
		System.out
				.println("  7,,,    ,,    77,,,,,,,,,,,,,,,,,,    7I,,,,,,,,7 7,,,,,,,,,,,,,,,,,,,,       ");
		System.out
				.println("  7,,,    ,,   ,,,,,,,,,,,,,,,,,,,,,   ,,,,,,,,,,,   ,,,,,,+        ,,,, 7      ");
		System.out
				.println("   =,,    ,,   ,,,,,,,,,,,,=   =,,,    ,,,,,,,,,,     ,,,?7        ,,,:         ");
		System.out
				.println("   ,,,    ,,    ,,,     ,,=     ,,,7    ,,,7 ,,,=    7,,,,,,,,,,,,,,,,          ");
		System.out
				.println("  7,,,    ,,    ,,,     ,,I     ,,,          ,,,     ,,,,,,,,,,,,,,,            ");
		System.out
				.println("   ,,,   +,,    ,,,    7,,?     ,,,          ,,,     ,,, 7    ,,,7              ");
		System.out
				.println("   ,,,   ,,,   ~,,?     ,,,     ,,,         ,,,,         ,,   ,,,               ");
		System.out
				.println("    ,7   ,,    ,,,     ~,,,     7,,=       7,,,? 7,,    ,,7   ,,                ");
		System.out
				.println("        ,,,    ,,,     ,,,,      ,,=       ,,,,,,,,7   ,,,   ,,,                ");
		System.out
				.println("     7,,,,,    ,,,     ,,,,      ,,7      ,,,,,,,~    ,,,    ,,,=         7 ,,  ");
		System.out
				.println("  ,,,,,,,,7      7     ,,,,             7,,,,,,,7   7,,,     ,,,,,,,~I I:,,,,=  ");
		System.out
				.println(" ,,,,,,,,7             ,,,,             ,,,,,      +,,,     I,,,,,,,,,,,,,,,,   ");
		System.out
				.println("  7?,,I                ,,,,             ,,,7       ,,,       ,,,,,,,,,,,,,,,,   ");
		System.out
				.println("                       777                                       7 ,,,,,,,,,    ");
		System.out
				.println("                                                                        7       ");
		Properties props = System.getProperties();
		System.out.println("\n\n欢迎使用【师说CMS】\n\n");
		System.out.println("Windows: set MAVEN_OPTS=-Dfile.encoding=UTF-8");
		System.out.println("Linux: export MAVEN_OPTS=-Dfile.encoding=UTF-8");
		System.out.println("操作系统的名称\t\t" + props.getProperty("os.name"));
		System.out.println("操作系统的架构\t\t" + props.getProperty("os.arch"));
		System.out.println("操作系统的版本\t\t" + props.getProperty("os.version"));
		System.out.println("用户的账户名称\t\t" + props.getProperty("user.name"));
		System.out.println("用户的主目录\t\t" + props.getProperty("user.home"));
		System.out.println("用户的当前工作目录\t" + props.getProperty("user.dir"));
		System.out.println("运行时环境版本\t\t" + props.getProperty("java.version"));
		System.out.println("Java安装目录\t\t" + props.getProperty("java.home"));
		System.out
				.println("Java虚拟机供应商\t" + props.getProperty("java.vm.vendor"));
		System.out.println("Java虚拟机名称\t\t" + props.getProperty("java.vm.name"));

		System.out.println("\n\n【重要】在开始前，您需要配置 " + CMS_PROPERTIES
				+ "，此文件包含数据连接的相关信息。");
	}

	/**
	 * 
	 */
	private boolean importData() {
		console.readLine("\n按control+c推出，按其它键继续安装。。。\n");
		Connection conn = null;
		PreparedStatement stmt = null;
		try {
			BufferedInputStream bis = new BufferedInputStream(
					new FileInputStream(CMS_PROPERTIES));
			Properties props = new Properties();
			props.load(bis);
			String url = props.getProperty("jdbc.url");
			String driver = props.getProperty("jdbc.driverClass");
			String username = props.getProperty("jdbc.user");
			String password = props.getProperty("jdbc.password");
			Class.forName(driver).newInstance();
			conn = (Connection) DriverManager.getConnection(url, username,
					password);
			ScriptRunner runner = new ScriptRunner(conn);
			runner.setErrorLogWriter(null);
			runner.setLogWriter(null);
			runner.runScript(new InputStreamReader(new FileInputStream(
					CMS_INSTALL_SQL), "UTF-8"));
			return true;
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getMessage());
			return false;
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getMessage());
			}
		}
	}
}
