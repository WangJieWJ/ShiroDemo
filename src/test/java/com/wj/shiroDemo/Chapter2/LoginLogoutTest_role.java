package com.wj.shiroDemo.Chapter2;

import junit.framework.Assert;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;

/**
 * Title:
 * Description:
 * <p>
 * Project: ShiroDemo
 * Create User: wangjie
 * Create Time: 2017/1/20 0020
 */
public class LoginLogoutTest_role {

    @Test
    public void testHasRole() {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-role.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("wang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //判断拥有角色：role1
        Assert.assertEquals(true, subject.hasRole("role1"));
        //判断拥有角色 : role1 role2
        Assert.assertEquals(true, subject.hasAllRoles(Arrays.asList("role1", "role4")));
        //判断拥有角色
        boolean[] roles = subject.hasRoles(Arrays.asList("role1", "role2", "role3"));
        for (boolean flag : roles) {
            System.out.println("是否具有权限：" + flag);
        }

        //Shiro提供hasRole/hasRoles/hasAllRoles方法来查看用户是否具有某个权限，但是没有提供来判断是否具有某些权限中的某个权限

        try {
            subject.checkRole("role2");
            System.out.println("具有此权限");
        } catch (UnauthorizedException e) {
            System.out.println("不具有此权限");
        }

        //Shiro提供checkRole/checkRoles来判断用户是否具有某个权限，与上面的方法不同的是当他判断为假的情况时，会抛出异常。

        subject.logout();
    }
}
