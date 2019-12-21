package com.aaa.sm.config;

import com.aaa.sm.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fileName:MyShiroRealm
 * description:
 * author:zz
 * createTime:2019/11/14 10:38
 * version:1.0.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        Object userName = authenticationToken.getPrincipal();
        Map map = new HashMap();
        map.put("userName",userName);
        //根据用户名查询列表
        List<Map> userMapList = userService.getListByParam(map);
        //判断
        if(userMapList!=null&&userMapList.size()>0){
            Map userMap = userMapList.get(0);
            //SecurityUtils.getSubject().getSession().setAttribute("userInfo",userMap);
            return new SimpleAuthenticationInfo(userMap.get("USER_NAME")+"",
                    userMap.get("PASSWORD")+"",getName());
        }else {
            throw new AccountException();
        }
    }
}
