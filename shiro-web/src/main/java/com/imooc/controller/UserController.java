package com.imooc.controller;

import com.imooc.vo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
 * @Package Name :com.imooc.controller
 * @Author : 1643091610@qq.com
 * @Date :2019 年 05月 05 日 19:24
 * @ModifcationHistory : ------Who----------When---------------What----------
 */
@Controller
public class UserController {

    @RequestMapping(value = "/subLogin", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String subLogin(User user) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUsername(), user
                .getPassword());
        try {
            token.setRememberMe(user.isRememberMe());
            subject.login(token);
        } catch (Exception e) {
            return e.getMessage();
        }
        if (subject.hasRole("admin")){
            return "有admin权限";
        }
        return "无admin权限";
    }

    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String  testRole(){
        return "testRole success";
    }

    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    @ResponseBody
    public String  testRole1(){
        return "testRole1 success";
    }

    @RequestMapping(value = "/testPerms",method = RequestMethod.GET)
    @ResponseBody
    public String  testPerms(){
        return "testPerms success";
    }
    @RequestMapping(value = "/testPerms1",method = RequestMethod.GET)
    @ResponseBody
    public String  testPerms1(){
        return "testPerms1 success";
    }


   /* @RequiresRoles("admin")
    @RequestMapping(value = "/testRole",method = RequestMethod.GET)
    @ResponseBody
    public String  testRole(){
        return "testRole success";
    }

    @RequiresPermissions("XXX")
    @RequestMapping(value = "/testRole1",method = RequestMethod.GET)
    @ResponseBody
    public String  testRole1(){
        return "testRole success";
    }*/
}
