package com.aaa.sm.service;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * fileName:UserService
 * description:
 * author:zz
 * createTime:2019/11/12 10:43
 * version:1.0.0
 */
public interface UserService {

    /**
     * 通用查询方法
     * @param map
     * @return
     */
    List<Map> getListByParam(Map map);


    /**
     * 根据用户名查询角色列表
     * @param userName
     * @return
     */
    List<Map> getRoleListByUserName(String userName);
}
