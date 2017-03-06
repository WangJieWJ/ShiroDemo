package com.wj.shiroDemo.Chapter2;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Authorizer;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Title:
 * Description:
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/20 0020
 */
public class LoginLogoutTest_permission {

    @Test
    public void testPermission(){
        Factory<SecurityManager> factory=new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        SecurityManager securityManager=factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject=SecurityUtils.getSubject();
        UsernamePasswordToken token=new UsernamePasswordToken("WangJie","wangjie");
        try {
            subject.login(token);
        }catch (AuthenticationException e){
            System.out.println("登陆出错");
        }
        try {
            subject.checkPermission("user:create");
            System.out.println("具有创建用户的权限");
        }catch (UnauthorizedException e){
            System.out.println("不具有创建用户的权限");
        }

        try {
            subject.checkPermission("user:view");
            System.out.println("具有查看用户的权限");
        }catch (UnauthorizedException e){
            System.out.println("不具有查看用户的权限");
        }
        subject.logout();
    }
}
