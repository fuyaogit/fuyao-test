package com.linktrust.fuyao.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * @description 环信群操作工具类
 * @author liudz
 * @date 2017年4月14日
 */
public class GroupUtils {

	/**
	 * @description 创建环信IM用户
	 * @author liudz
	 * @date 2017年4月14日
	 * @param @param loginName 登录名
	 * @param @param password  加密后的密码串
	 * @param @param realName  姓名
	 * @param @return 若成功直接返回true,失败则返回整个消息体
	 * 	{
		  "flag": true,  --结果(true/false)
		  "code": 0,     --错误代码
		  "message": "string",  --消息体
		  "data": {},  --消息数据
		  "extra": {}  --额外数据
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object createIMUser(String loginName,String password,String realName) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("loginName", loginName);
		param.put("loginPwd", password);
		param.put("userName", realName);
		JSONObject result = HttpClient.get(Constant._GROUP_ADDR+"easermob/createEasemobIM", param);
		if(result.getBoolean("flag")){
			return true;
		}else{
			return result;
		}
	}
	
	/**
	 * @description 创建环信群
	 * @author liudz
	 * @date 2017年4月14日
	 * @param @param groupName 群名(对应班级名称,非空)
	 * @param @param description 描述(对应班级描述)
	 * @param @param adminName 管理员登录名(班主任登录名,非空),班主任为空时,传创建人的登录名
	 * @param @param memberList 成员登录名集合
	 * @param @return 成功：返回新增群的id,失败则返回消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object createGroup(String groupName,String description,String adminName,List<String> memberList) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupName", groupName);
		if(description == null || description.equals("")){
			description = "班级群";
		}
		param.put("desc", description);
		param.put("owner", adminName);
		if(memberList != null && memberList.size()>0){
			StringBuffer sb = new StringBuffer();
			boolean isFirst = true;
			for(String str:memberList){
				if(!isFirst){
					sb.append(",");
				}
				sb.append(str);
				isFirst = false;
			}
			param.put("members", sb.toString());
		}
		JSONObject result = HttpClient.post(Constant._GROUP_ADDR+"easermob/createChatGroup", param);
		if(result.getBoolean("flag")){
			return result.getString("data");
		}else{
			return result;
		}
	}
	
	/**
	 * @description 删除环信群
	 * @author liudz
	 * @date 2017年4月17日
	 * @param @param groupId 群id
	 * @param @return 成功：返回true,失败则返回消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object deleteGroup(String groupId) throws Exception{
		if(groupId != null){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("groupId", groupId);
			JSONObject result = HttpClient.get(Constant._GROUP_ADDR+"easermob/deleteChatGroup", param);
			if(result.getBoolean("flag")){
				return true;
			}else{
				return result;
			}
		}
		return null;
	}
	
	/**
	 * @description 修改环信群
	 * @author liudz
	 * @date 2017年4月17日
	 * @param @param groupId 群id
	 * @param @param groupName 群名
	 * @param @param description 群描述
	 * @param @param maxusers 群成员最大数量
	 * @param @return 成功：返回true,失败则返回消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object modifyGroup(String groupId,String groupName,String description,String ... maxusers) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupId", groupId);
		param.put("groupName", groupName);
		if(description != null){
			param.put("description", description);
		}
		if(maxusers.length>0){
			param.put("maxusers", maxusers[0]);
		}
		JSONObject result = HttpClient.get(Constant._GROUP_ADDR+"easermob/modifyChatGroup", param);
		if(result.getBoolean("flag")){
			return true;
		}else{
			return result;
		}
	}
	
	/**
	 * @description 从环信群中移除指定的用户
	 * @author liudz
	 * @date 2017年4月14日
	 * @param @param groupId 群id
	 * @param @param loginName 待移除人的登录名
	 * @param @return 若成功直接返回true,失败则返回整个消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object deleteUserForGroup(String groupId,String loginName) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupId", groupId);
		param.put("loginName", loginName);
		JSONObject result = HttpClient.get(Constant._GROUP_ADDR+"easermob/deleteChatGroupUser", param);
		if(result.getBoolean("flag")){
			return result.getString("data");
		}else{
			return result;
		}
	}
	
	
	
	/**
	 * @description 为环信群中添加指定的用户
	 * @author liudz
	 * @date 2017年4月14日
	 * @param @param groupId 群id
	 * @param @param loginName 待移除人的登录名
	 * @param @return 若成功直接返回true,失败则返回整个消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object addUserForGroup(String groupId,String loginName) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupId", groupId);
		param.put("loginName", loginName);
		JSONObject result = HttpClient.get(Constant._GROUP_ADDR+"easermob/addChatGroupUser", param);
		if(result.getBoolean("flag")){
			return result.getString("data");
		}else{
			return result;
		}
	}
	
	/**
	 * @description 更换群管理员
	 * @author liudz
	 * @date 2017年4月14日
	 * @param @param groupId 群id
	 * @param @param loginName 新管理员登录名
	 * @param @param removeOldAdmin 默认的：老管理员将变为普通群成员,此参数控制是否把老管理员从群里移除
	  * @param @return 若成功直接返回true,失败则返回整个消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object changeAdminForGroup(String groupId,String loginName,boolean removeOldAdmin) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("groupId", groupId);
		param.put("newOwner", loginName);
		param.put("remove", removeOldAdmin);
		/*
		 * 修改群管理员时，先将新管理员加入群中
		 */
		addUserForGroup(groupId, loginName);
		JSONObject result = HttpClient.post(Constant._GROUP_ADDR+"easermob/transferChatGroupOwner", param);
		if(result.getBoolean("flag")){
			return true;
		}else{
			return result;
		}
	}
	/**
	 * @Description： TODO 修改环信密码
	 * @author: GUO WEI 
	 * @since: 2017年4月15日 下午2:49:47
	 * @param @param loginName 环信用户登录名
	 * @param @param password 修改密码
	 * @param @return 若成功直接返回true,失败则返回整个消息体
	 * 	{
		  "flag": true,
		  "code": 0,
		  "message": "string",
		  "data": {},
		  "extra": {}
		}
	 * @param @throws Exception 业务层需捕获并处理http请求异常
	 */
	public static Object changeUserPwdForGroup(String loginName,String password) throws Exception{
		Map<String,Object> param = new HashMap<String,Object>();
		param.put("loginName", loginName);
		param.put("passWord", password);
		JSONObject result = HttpClient.get(Constant._GROUP_ADDR+"easermob/modifyIMUserPassWord", param);
		if(result.getBoolean("flag")){
			return true;
		}else{
			return result;
		}
	}

	
}
