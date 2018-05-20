package com.linktrust.fuyao.utils;

import java.net.URLDecoder;

public class Charset {

	public static String encode(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		try {
			if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder()
					.canEncode(str)) {
				return new String(str.getBytes("ISO-8859-1"), "UTF-8");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String decode(String str) {
		if (str == null || "".equals(str)) {
			return null;
		}
		try {
			if (java.nio.charset.Charset.forName("UTF-8").newEncoder()
					.canEncode(str)) {
				return URLDecoder.decode(str, "UTF-8");
			} else if (java.nio.charset.Charset.forName("ISO-8859-1")
					.newEncoder().canEncode(str)) {
				return URLDecoder.decode(str, "ISO-8859-1");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}

}
