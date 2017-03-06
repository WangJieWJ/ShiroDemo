package com.wj.shiroDemo;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Title:
 * Description:
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/19 0019
 */
public class MyRealm1 implements Realm{

    @Override
    public String getName() {
        return "MyRealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String)authenticationToken.getPrincipal();
        String password=new String((char[])authenticationToken.getCredentials());
        if(!"WangJie".equals(username)){
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"wangjie".equals(password)){
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个AuthenticationInfo实现；
        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
