package com.imooc.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
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
 * @Date :2019 年 04月 18 日 15:27
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class JdbcRealmTest {

    DruidDataSource dataSource=new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("123");
    }

    @Test
    public void testAuthentication(){

        JdbcRealm jdbcRealm=new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
//        jdbcRealm.setPermissionsLookupEnabled(true);   //默认为false 不查询权限是否具有增删改查的权限

        String sql="select password from test_user where user_name=?";
        jdbcRealm.setAuthenticationQuery(sql);

        String roleSql="select role_name from test_user_role where user_name=?";
        jdbcRealm.setUserRolesQuery(roleSql);

        //1、构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        //2、主题提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject= SecurityUtils.getSubject();

        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken("xiaoming","654321");
        subject.login(usernamePasswordToken);

        System.out.println("isAuthenticated："+subject.isAuthenticated());

        /*subject.checkRole("admin");

        subject.checkRoles("admin","user");*/

        subject.checkRole("user");

    }

}
