package com.wj.shiroDemo.Chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Title:
 * Description: 使用自定义的Realm来模拟用户的登陆与登出
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/19 0019
 */
public class LoginLogoutTest_realm {

    @Test
    public void testHelloWorld() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            //登陆出错
        }

        Assert.assertEquals(true, subject.isAuthenticated()); //断言用户已经登陆

        subject.logout();
    }
}
