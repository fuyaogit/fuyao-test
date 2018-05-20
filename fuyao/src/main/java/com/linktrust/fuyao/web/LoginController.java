package com.linktrust.fuyao.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;







import com.linktrust.fuyao.bean.User;
import com.linktrust.fuyao.service.LoginService;
import com.linktrust.fuyao.utils.RestResponse;

@RequestMapping("login")
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value="login",method=RequestMethod.POST)
	@ResponseBody
	RestResponse login(User user,HttpServletRequest request){
		
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userName", user.getUserName());
			map.put("passWord", user.getPassWord());
			User user2 = loginService.selectUserByLoginParam(map);
			if (user2 != null) {
				Subject currentUser = SecurityUtils.getSubject();
				if (currentUser.isAuthenticated()) {
					currentUser.logout();
				}
				UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(),
						user.getPassWord());
				currentUser.login(token);
				request.setAttribute("loginUser", user2);
				return RestResponse.success("登陆成功!");
			}else{
				return RestResponse.failure("用户名或密码错误!");
			}
		}catch(Exception e){
			return RestResponse.failure(e);
		}
		
	};
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	RestResponse updateUser(User user){
		
		try{
			User user2 = loginService.updateUser(user);
			if (user2!= null) {
				return RestResponse.success("修改成功!");
			}else{
				return RestResponse.failure("修改失败!");
			}
		}catch(Exception e){
			return RestResponse.failure(e);
		}
		
	};
}
