package com.linktrust.fuyao.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
 
/** 
 * <p>Title: </p> 
 * <p>Description: </p> 
 * <p>Copyright: Copyright (c) 2003</p> 
 * <p>Company: </p> 
 * @author unascribed 
 * @version 1.0 
 */ 
public class StringUtil { 
  private final static String[] hexDigits = { 
      "0", "1", "2", "3", "4", "5", "6", "7", 
      "8", "9", "a", "b", "c", "d", "e", "f"}; 
  /** 
   * 转换字节数组为16进制字串 
   * @param b 字节数组 
  * @return 16进制字串 
   */ 
  public static String byteArrayToHexString(byte[] b) { 
    StringBuffer resultSb = new StringBuffer(); 
    for (int i = 0; i < b.length; i++) { 
      resultSb.append(byteToHexString(b[i])); 
    } 
    return resultSb.toString(); 
  } 
	/**
	 * 验证是否为数字
	 * @Title: isNumeric 
	 * @Description: TODO 验证是否为数字
	 * @param str
	 * @return
	 * @return boolean
	 */
	 public static boolean isNumeric(String str) {
		    Pattern pattern = Pattern.compile("^[-\\+]?\\d+[\\.\\d+]?$");    
		    return pattern.matcher(str).matches();    
	 }
	 
  private static String byteToHexString(byte b) { 
    int n = b; 
    if (n < 0) 
      n = 256 + n; 
   int d1 = n / 16; 
    int d2 = n % 16; 
    return hexDigits[d1] + hexDigits[d2]; 
  } 
  public static String MD5Encode(String origin) { 
    String resultString = null;
   try { 
     resultString=new String(origin); 
//      MessageDigest md = MessageDigest.getInstance("MD5"); 
//      resultString=byteArrayToHexString(md.digest(resultString.getBytes())); 
     resultString=md5AndSha(resultString);
    } 
    catch (Exception ex) { 
    } 
    return resultString; 
    
  } 

 

 public static String getIpAddr(HttpServletRequest request) {
     String ip = request.getHeader("x-forwarded-for");
     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("Proxy-Client-IP");
     }
     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getHeader("WL-Proxy-Client-IP");
     }
     if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
         ip = request.getRemoteAddr();
     }
     return ip;
 }

 	public static int setRowHeight(String value,int width) { 
 		int height = 0;
 		if(value!=null){
 			int len= 0;
 			for(int i =0;i<value.length();i++){
 				if(value.charAt(i)>0x80){
 					len++;
 					len++;
 				}else{
 					len++;
 				} 
 			} 
 			height = (int)Math.ceil( (len==0?1:len)/(width/10.0*8))*300;
 		}
	    return height; 
	 } 
 	 

  	/**
	 * 日期转String
	 */
		public static String toExcelString(Date date){
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
			String str = sf.format(date);
			String month = str.split("-")[1];
			if(month.equals("01")){
				str = str.split("-")[2]+"-"+"Jan"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("02")){
				str = str.split("-")[2]+"-"+"Feb"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("03")){
				str = str.split("-")[2]+"-"+"Mar"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("04")){
				str = str.split("-")[2]+"-"+"Apr"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("05")){
				str = str.split("-")[2]+"-"+"May"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("06")){
				str = str.split("-")[2]+"-"+"Jun"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("07")){
				str = str.split("-")[2]+"-"+"Jul"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("08")){
				str = str.split("-")[2]+"-"+"Aug"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("09")){
				str = str.split("-")[2]+"-"+"Sep"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("10")){
				str = str.split("-")[2]+"-"+"Oct"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("11")){
				str = str.split("-")[2]+"-"+"Nov"+"-"+str.split("-")[0].substring(2, 4);
			}else if(month.equals("12")){
				str = str.split("-")[2]+"-"+"Dec"+"-"+str.split("-")[0].substring(2, 4);
			}else{
				str = "";
			}
			return str;
		}
	
		/**
		 * 转换日期格式为DATE类型
		 */
		public static Date toDate(String str){
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try{
			if(!str.equals("")&&str.split("-").length==3){
				String month = str.split("-")[1];
				if(month.equals("Jan")){
					str = str.split("-")[2]+"-"+"01"+"-"+str.split("-")[0];
				}else if(month.equals("Feb")){
					str = str.split("-")[2]+"-"+"02"+"-"+str.split("-")[0];
				}else if(month.equals("Mar")){
					str = str.split("-")[2]+"-"+"03"+"-"+str.split("-")[0];
				}else if(month.equals("Apr")){
					str = str.split("-")[2]+"-"+"04"+"-"+str.split("-")[0];
				}else if(month.equals("May")){
					str = str.split("-")[2]+"-"+"05"+"-"+str.split("-")[0];
				}else if(month.equals("Jun")){
					str = str.split("-")[2]+"-"+"06"+"-"+str.split("-")[0];
				}else if(month.equals("Jul")){
					str = str.split("-")[2]+"-"+"07"+"-"+str.split("-")[0];
				}else if(month.equals("Aug")){
					str = str.split("-")[2]+"-"+"08"+"-"+str.split("-")[0];
				}else if(month.equals("Sep")){
					str = str.split("-")[2]+"-"+"09"+"-"+str.split("-")[0];
				}else if(month.equals("Oct")){
					str = str.split("-")[2]+"-"+"10"+"-"+str.split("-")[0];
				}else if(month.equals("Nov")){
					str = str.split("-")[2]+"-"+"11"+"-"+str.split("-")[0];
				}else if(month.equals("Dec")){
					str = str.split("-")[2]+"-"+"12"+"-"+str.split("-")[0];
				}else if(month.equals("")){
					date = null;
				}
				if(str.split("-")[0].equals("")){
					date = null;
				}
				if(date!=null){
				String year = str.split("-")[0];
				if(Integer.parseInt(year)>50){
					year = "19"+year;
				}else{
					year = "20"+year;
				}
				
				str = year+"-"+str.split("-")[1]+"-"+str.split("-")[2]; 
					try {
						date = df.parse(str);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
				}
			}else{
				date = null;
			}
			}catch (Exception e) {
				date = null;
			}
			return date;
		}
		/**
		 * 加密
		 * @param inputText
		 * @return
		 */
		public static String e(String inputText) {
			return md5(inputText);
		}

		/**
		 * 二次加密，
		 * @param inputText
		 * @return
		 */
		public static String md5AndSha(String inputText) {
			return sha(md5(inputText));
		}

		/**
		 * md5加密
		 * @param inputText
		 * @return
		 */
		public static String md5(String inputText) {
			return encrypt(inputText, "md5");
		}

		/**
		 * sha加密
		 * @param inputText
		 * @return
		 */
		public static String sha(String inputText) {
			return encrypt(inputText, "sha1");
		}

		/**
		 * md5或者sha-1加密
		 * @param inputText
		 *            要加密的内容
		 * @param algorithmName
		 *            加密算法名称：md5或者sha-1，不区分大小写
		 * @return
		 */
		private static String encrypt(String inputText, String algorithmName) {
			if (inputText == null || "".equals(inputText.trim())) {
				throw new IllegalArgumentException("请输入要加密的内容");
			}
			if (algorithmName == null || "".equals(algorithmName.trim())) {
				algorithmName = "md5";
			}
			String encryptText = null;
			try {
				MessageDigest m = MessageDigest.getInstance(algorithmName);
				m.update(inputText.getBytes("UTF8"));
				byte s[] = m.digest();
				return hex(s);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return encryptText;
		}

		/**
		 * 返回十六进制字符串
		 * @param arr
		 * @return
		 */
		private static String hex(byte[] arr) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < arr.length; ++i) {
				sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		
		 public static void main(String[] args){  
//			 String str = "";String str1 = ( "".equals(str))?null:"";
//			 System.out.println(str1);
//			 System.out.println(Arrays.toString(new String[]{"0","1","0"}).replaceAll(" ", "").replaceAll("\\[", "").replaceAll("\\]", ""));
//		    System.err.println(MD5Encode("123456")); 
//		    System.err.println(com.bugull.mongo.utils.StringUtil.encodeMD5("sha1"));
//		    System.out.println(setRowHeight("",10) ); 
		    System.out.println(md5AndSha("123456"));
		  } 
 
}  