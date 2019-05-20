package com.imooc.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.realm.text.IniRealm;
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
 * @Date :2019 年 04月 18 日 15:15
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class IniRealmTest {

    @Test
    public void testAuthentication(){

        IniRealm iniRealm=new IniRealm("classpath:user.ini");

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);
        //2、主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("mask","123456");
        subject.login(usernamePasswordToken);

        System.out.println("isAuthenticated："+subject.isAuthenticated());

        subject.checkRole("admin");

        subject.checkPermission("user:update");
    }
}
