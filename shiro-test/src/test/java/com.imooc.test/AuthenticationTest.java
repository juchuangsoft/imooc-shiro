package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

/**
 * ━━━━━━神兽出没━━━━━━
 * 　　　┏┓　　　┏┓
 * 　　┏┛┻━━━┛┻┓
 * 　　┃　　　　　　　┃
 * 　　┃　　　━　　　┃
 * 　　┃　┳┛　┗┳　┃
 * 　　┃　　　　　　　┃
 * 　　┃　　　┻　　　┃
 * 　　┃　　　　　　　┃
 * 　　┗━┓　　　┏━┛Code is far away from bug with the animal protecting
 * 　　　　┃　　　┃    神兽保佑,代码无bug
 * 　　　　┃　　　┃
 * 　　　　┃　　　┗━━━┓
 * 　　　　┃　　　　　　　┣┓
 * 　　　　┃　　　　　　　┏┛
 * 　　　　┗┓┓┏━┳┓┏┛
 * 　　　　　┃┫┫　┃┫┫
 * 　　　　　┗┻┛　┗┻┛
 * <p>
 * ━━━━━━感觉萌萌哒━━━━━━
 *
 * @Project : imooc-shiro
 * @Description :  [一句话描述该类的功能]
 * @Package Name :com.imooc.test
 * @Author : 1643091610@qq.com
 * @Date :2019 年 04月 12 日 0:44
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class AuthenticationTest {

    SimpleAccountRealm simpleAccountRealm=new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("mask","123456","admin","user");
    }

    @Test
    public void testAuthentication(){
        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(simpleAccountRealm);
        //2、主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("mask","123456");
        subject.login(usernamePasswordToken);

        System.out.println("isAuthenticated："+subject.isAuthenticated());

        subject.checkRoles("admin","user");
    }
}
