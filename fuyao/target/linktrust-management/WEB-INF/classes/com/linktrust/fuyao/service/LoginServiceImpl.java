package com.linktrust.fuyao.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;











import javax.xml.ws.ServiceMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.linktrust.fuyao.bean.Privilege;
import com.linktrust.fuyao.bean.User;
import com.linktrust.fuyao.dao.LoginMapper;
@Service
public class LoginServiceImpl implements LoginService{
	
	@Autowired
	private LoginMapper loginMapper;
	
	@Override
	@Cacheable(value="cacheManager",key="#map.get('userName').concat(#map.get('passWord'))")
	public User selectUserByLoginParam(Map<String, Object> map) {
		User user = null;
		user = loginMapper.selectUserByLoginParam(map);
		return user;
	}

	@Override
	@CachePut(value="cacheManager",key="#user.getUserName().concat(#user.getPassWord())")
	public User updateUser(User user) {
		user.setPassWord("123");
		loginMapper.updateUser(user);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", user.getUserName());
		map.put("passWord", user.getPassWord());
		return loginMapper.selectUserByLoginParam(map);
	}

	@Override
	public List<Privilege> selectAllPrivilege() {
		return loginMapper.selectAllPrivilege();
	}

	@Override
	public List<String> selectPriNameByUser(String userId) {
		return loginMapper.selectPriNameByUser(userId);
	}

}
