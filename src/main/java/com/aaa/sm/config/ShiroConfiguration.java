package com.aaa.sm.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * fileName:ShiroConfiguration
 * description:
 * author:zz
 * createTime:2019/11/14 10:32
 * version:1.0.0
 */
//@Configuration
public class ShiroConfiguration {

    /**
     * 拦截   跳转配置
     * @param securityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //设值
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //未认证跳转登录页面
        shiroFilterFactoryBean.setLoginUrl("/html/login.html");
        //认证成功后跳转的页面
        //shiroFilterFactoryBean.setSuccessUrl("/");
        //为授权跳转页面
        //shiroFilterFactoryBean.setUnauthorizedUrl("/");
        //拦截所有的/**= authc 的配置必须在最下面，所以使用LinkedHashMap
        Map chainDefinitionMap =new LinkedHashMap();
        //放开登录相关
        chainDefinitionMap.put("/html/login.html","anon");
        chainDefinitionMap.put("/user/login","anon");

        //放开静态资源
        chainDefinitionMap.put("/css/**","anon");
        chainDefinitionMap.put("/js/**","anon");
        chainDefinitionMap.put("/imgs/**","anon");

        //拦截除上面放开之外的所有请求
        chainDefinitionMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 把shiro的bean交给spring管理
     * @return
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
        return new LifecycleBeanPostProcessor();
    }

    /**
     * SecurityManager 核心配置
     * @param myRealm
     * @return
     */
    @Bean
    public SecurityManager securityManager(MyShiroRealm myRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(myRealm);
        return defaultWebSecurityManager;
    }

    /**
     * 自定义realm配置
     * @return
     */
    @Bean
    public MyShiroRealm myRealm(){
        return new MyShiroRealm();
    }
}
