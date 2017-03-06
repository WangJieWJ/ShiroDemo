package com.wj.shiroDemo.Chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AllSuccessfulStrategy;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Title:
 * Description:
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/19 0019
 */
public class LoginLogoutTest_jdbc_realm {

    @Test
    public void testJdbcRealm(){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("WangJie1","wangjie");
        try{
            subject.login(token);
            System.out.println("Success Connection MySQL");
        }catch (AuthenticationException e){
            //登陆失败
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();

    }
}
