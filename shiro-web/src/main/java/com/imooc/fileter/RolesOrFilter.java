package com.imooc.fileter;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

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
 * @Package Name :com.imooc.fileter
 * @Author : 1643091610@qq.com
 * @Blog ：https://www.cnblogs.com/xiaohaojs/
 * @Date :2019 年 05月 09 日 19:34
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
public class RolesOrFilter extends AuthorizationFilter {

    protected boolean isAccessAllowed(javax.servlet.ServletRequest servletRequest, javax.servlet.ServletResponse servletResponse, Object o) throws Exception {
        Subject subject=getSubject(servletRequest,servletResponse);
        String[] roles=(String[])o;
        if (roles==null||roles.length==0){
            return  true;
        }
        for (String role:roles){
            if(subject.hasRole(role)){
                return true;
            }
        }
        return false;
    }
}
