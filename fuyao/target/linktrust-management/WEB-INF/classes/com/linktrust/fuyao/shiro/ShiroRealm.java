package com.linktrust.fuyao.shiro;

import java.util.List;

import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.linktrust.fuyao.service.LoginService;

public class ShiroRealm extends AuthorizingRealm{
	
	@Autowired
    ClientShiroFilterFactoryBean shiroFilterFactoryBean;
    @Autowired
    private LoginService loginService;

    /**
     * 权限认证: 访问远程服务器获取登录权限
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        // 构造返回对象
        SimplePrincipalCollection sprinc = (SimplePrincipalCollection) principals;
        // 通过getPrimaryPrincipal获取用户信息。
        String userPrincipal = (String) sprinc.getPrimaryPrincipal();
        if (userPrincipal == null) {
            return null;
        }
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
        List<String> list = loginService.selectPriNameByUser(userPrincipal);
        // 给用户赋予权限
        simpleAuthorInfo.addStringPermissions(list);
        return simpleAuthorInfo;
    }

    /**
     * 登陆认证
     * 子系统的登录不做加密验证 -->服务端通过子系统必然通过
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        try {
            UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(token.getUsername(),
                    token.getPassword(), getName());
            return authcInfo;
        } catch (AuthenticationException e) {
            throw new AccountException("登录token验证失败");
        }
    }
}
