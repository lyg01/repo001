package com.aaa.sm.controller;

import com.aaa.sm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * fileName:UserLoginController
 * description:
 * author:zz
 * createTime:2019/11/14 11:10
 * version:1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;
    /**
     * 登录
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("login")
    public Object login(String userName,String passWord){
        //收集用户输入信息
        UsernamePasswordToken usernamePasswordToken = new
                UsernamePasswordToken(userName,passWord);
        //获取用户对象
        Subject subject = SecurityUtils.getSubject();
        try {
            //登录
            subject.login(usernamePasswordToken);
            //存储session
            Map map = new HashMap();
            map.put("userName",userName);
            subject.getSession().setAttribute("userInfo",userService.getListByParam(map).get(0));
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    /**
     * 获取session
     * @return
     */
    @RequestMapping("getSession")
    public Object getSession(){
        return (Map)SecurityUtils.getSubject().getSession().getAttribute("userInfo");
    }

    @RequestMapping("loginOut")
    public Object loginOut(){
        try {
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
