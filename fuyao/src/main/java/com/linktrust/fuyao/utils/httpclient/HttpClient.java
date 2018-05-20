package com.linktrust.fuyao.utils.httpclient;

import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import com.alibaba.fastjson.JSONObject;

public class HttpClient {

	// public static class Params {
	//
	// public static StringBuffer paramPath = null;
	//
	// public Params append(String key, Object value) {
	// if (paramPath == null) {
	// paramPath = new StringBuffer();
	// }
	// paramPath.append(key).append("=").append(value).append("&");
	// return this;
	// }
	// }

	/**
	 * post请求 path：url地址 map集合传递参数
	 *
	 */
	public static JSONObject post(String path, Map<String, Object> params)
			throws Exception {
		URL url = new URL(path);
		// 打开连接
		URLConnection conn = url.openConnection();
		// 设置通用属性
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		// 设置post请求发送
		conn.setDoInput(true);
		conn.setDoOutput(true);
		return callback(conn, concat(params), true);

	}

	/**
	 * get请求 path：url地址 map集合传递参数
	 *
	 */
	public static JSONObject get(String path, Map<String, Object> params)
			throws Exception {
		URL url = new URL(path + "?" + concat(params));
		// 打开连接
		URLConnection conn = url.openConnection();
		// 设置通用属性
		conn.setRequestProperty("Accept-Charset", "utf-8");
		conn.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		return callback(conn, null, false);
	}

	private static String concat(Map<String, Object> params) {
		StringBuffer pathParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			Iterator<Map.Entry<String, Object>> iter = params.entrySet()
					.iterator();
			while (iter.hasNext()) {
				Map.Entry<String, Object> entry = iter.next();
				pathParams.append(entry.getKey()).append("=")
						.append(entry.getValue()).append("&");
			}
			pathParams.delete(pathParams.length() - 1, pathParams.length());
		}
		return pathParams.toString();
	}

	private static JSONObject callback(URLConnection conn, String params,
			boolean type) {
		PrintWriter out = null;
		Scanner scan = null;
		try {
			if (type) {// 用于发送请求
				out = new PrintWriter(conn.getOutputStream());
				// 发送请求
				out.print(params);
				// 释放缓冲区
				out.flush();
			}
			// 定义回应信息读取类
			scan = new Scanner(conn.getInputStream());
			StringBuffer result = new StringBuffer();
			while (scan.hasNext()) {
				result.append(scan.next()).append("\n");
			}
			
			return JSONObject.parseObject(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				out.close();
			}
			if (scan != null) {
				scan.close();
			}
		}
		return null;
	}

}
