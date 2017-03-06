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
public class MyRealm2 implements Realm{
    @Override
    public String getName() {
        return "MyRealm2";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username=(String) authenticationToken.getPrincipal();
        String password=new String((char[])authenticationToken.getCredentials());
        if(!"zhang".equals(username)){
            throw new UnknownAccountException();
        }

        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
