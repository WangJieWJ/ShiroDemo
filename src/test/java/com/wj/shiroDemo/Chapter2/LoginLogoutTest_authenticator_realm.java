package com.wj.shiroDemo.Chapter2;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AuthenticationStrategy;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.List;

/**
 * Title:
 * Description:
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/20 0020
 */
public class LoginLogoutTest_authenticator_realm {

    @Test
    public void testHelloWorld() {

        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-authenticator-all-success.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("WangJie","wangjie");
        try{
            subject.login(token);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }

        Assert.assertEquals(true,subject.isAuthenticated());

        System.out.println("成功登陆！");

        PrincipalCollection principalCollection=subject.getPrincipals();
        List<String> list=principalCollection.asList();
        for(String Str: list){
            System.out.println("所获得的身份为："+Str);
        }

        subject.logout();
    }
}
