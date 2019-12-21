package com.aaa.sm.dao;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * fileName:UserDao
 * description:
 * author:zz
 * createTime:2019/11/12 10:39
 * version:1.0.0
 */
public interface UserDao {

    /**
     * 通用查询方法
     * @param map
     * @return
     */
    @Select("<script>select * from tb_user <where>" +
            "<if test='userName!=null'> and user_name =#{userName} </if>"+
            "<if test='passWord!=null'> and password =#{passWord} </if>"+
            "</where></script>")
    List<Map> getListByParam(Map map);

    /**
     * 根据用户名查询角色列表
     * @param userName
     * @return
     */
    @Select("select * from tb_role where role_id in(\n" +
            "select role_id from tb_user_role ur where ur.user_id=(\n" +
            " select user_id from tb_user where user_name=#{userName} \n" +
            "))")
    List<Map> getRoleListByUserName(String userName);
}
