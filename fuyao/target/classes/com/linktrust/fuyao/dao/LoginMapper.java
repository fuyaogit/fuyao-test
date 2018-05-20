package com.linktrust.fuyao.dao;

import java.util.List;
import java.util.Map;

import com.linktrust.fuyao.bean.Privilege;
import com.linktrust.fuyao.bean.User;

public interface LoginMapper {
	
	User selectUserByLoginParam(Map<String,Object> map);
	
	Integer updateUser(User user);
	
	/**
	 * 查询所有权限
	 * @return
	 */
	List<Privilege> selectAllPrivilege ();
	
	/**
	 * 查询用户所有的权限字符串
	 * @param userId
	 * @return
	 */
	List<String> selectPriNameByUser(String userId);
}
