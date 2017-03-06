package com.wj.shiroDemo.Chapter2;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Title:
 * Description: 多Realm操作
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/19 0019
 */
public class LoginLogoutTest_multi_realm {

    @Test
    public void testHelloWorld(){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("zhang","123");
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            //登陆出错
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }
}
