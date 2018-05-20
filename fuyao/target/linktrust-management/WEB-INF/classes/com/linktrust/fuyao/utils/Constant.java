package com.linktrust.fuyao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

public class Constant {

	/**
	 * 默认密码123456
	 */
	public static final String _PAWD = "10470c3b4b1fed12c3baac014be15fac67c6e815";
	/**
	 * 系统标识
	 */
	public static final String SYS_ID="linktrust-management";

	private static final String FDFS_CLIENT_CONF = "dev/properties/web_server.conf";
	private static final String _IMG = "img_addr";
	private static final String _WEB = "web_addr";
	private static final String _OA = "oa_addr";
	
	
	public static String _IMG_ADDR;
	public static String _WEB_ADDR;
	public static String _OA_ADDR;
	
	/**
	 * 环信用户、群操作相关
	 */
	private static final String _GROUP = "group_addr";
	public static String _GROUP_ADDR;

	public static String getUrl(String url) {
		if (url != null && !"".equals(url)) {
			return Constant._IMG_ADDR + url;
		}
		return null;
	}

	public static String setUrl(String url) {
		if (url != null && !"".equals(url)) {
			if (!url.contains(_IMG_ADDR)) {
				return url;
			}
			return url.substring(_IMG_ADDR.length());
		}
		return null;
	}
	
	/**
	 * 获取webserver的服务地址
	 * 
	 * @Title:
	 * @Description: TODO 获取webserver的服务地址
	 * @return
	 * @throws Exception
	 * @return String
	 */
	static {
		// 查找和读取配置文件及文件参数
		ClassPathResource cpr = new ClassPathResource(FDFS_CLIENT_CONF);
		InputStream inCfg = cpr.getClassLoader().getResourceAsStream(
				FDFS_CLIENT_CONF);
		Properties prop = new Properties();
		try {
			prop.load(inCfg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		_IMG_ADDR = "http://" + prop.getProperty(_IMG) + "/";
		_WEB_ADDR = "http://" + prop.getProperty(_WEB) + "/";
		_OA_ADDR = "http://" + prop.getProperty(_OA) + "/";
		_GROUP_ADDR = "http://" + prop.getProperty(_GROUP) + "/";
	}

}
