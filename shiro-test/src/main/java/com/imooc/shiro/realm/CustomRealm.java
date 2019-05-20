package com.imooc.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * @Description :  [自定义Realm]
 * @Package Name :com.imooc.shiro.realm
 * @Author : 1643091610@qq.com
 * @Date :2019 年 04月 18 日 20:34
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class CustomRealm extends AuthorizingRealm {

    Map<String, String> userMap = new HashMap<String, String>(16);

    {
        userMap.put("mask", "84c44e41dd52707cf02fec7757dc9ca5");
        super.setName("customRealm");
    }

    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        ///1、从主体传过来的认证信息中，获得用户名
        String userName = (String) principalCollection.getPrimaryPrincipal();
        //2、从数据库或者缓存中获取角色数据
        Set<String> roles = getRolesByUserName(userName);

        Set<String> permission = getPermissionByUserName(userName);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permission);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * @param userName
     * @return
     */
    private Set<String> getPermissionByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;
    }

    /**
     * @param userName
     * @return
     */
    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1、从主体传过来的认证信息中，获得用户名
        String userName = (String) authenticationToken.getPrincipal();

        //2、通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);

        if (password == null) {
            return null;
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("mask", password, "customRealm");
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("Mask"));
        return authenticationInfo;
    }

    /**
     * 模拟数据库查询凭证
     *
     * @param userName
     * @return
     */
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }

    public static void main(String[] args) {
        Md5Hash md5Hash = new Md5Hash("123456", "Mask");
        System.out.println(md5Hash);
    }
}
