package com.wj.shiroDemo;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Title:
 * Description:
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/20 0020
 */
public class MyRealm3 implements Realm {
    @Override
    public String getName() {
        return "MyRealm3";
    }

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        String password = new String((char[]) token.getCredentials());

        if (!"WangJie".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"wangjie".equals(password)) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo("1152689483@qq.com", password, getName());
    }
}
