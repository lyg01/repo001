package com.aaa.sm.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * fileName:EmpService
 * description:
 * author:zz
 * createTime:2019/11/13 11:32
 * version:1.0.0
 */
public interface EmpService {
    /**
     * 根据参数查询列表
     * @param map
     * @return
     */
    List<Map> getList(Map map);
    /**
     * 带条件查询总条数
     * @param map
     * @return
     */
    int getPageCount(Map map);
    /**
     * 添加
     * @param map
     * @return
     */
    int add(Map map);


    /**
     * 更新
     * @param map
     * @return
     */
    int update(Map map);

    /**
     * 删除
     * @param deptNo
     * @return
     */
    int delete(int deptNo);

    /**
     * 获取所有部门，用于页面数据绑定
     * @return
     */
    List<Map> getDeptType();

    /**
     * 获取所有职位，用于页面数据绑定
     * @return
     */
    List<Map> getJob();

    /**
     * 获取上司，用于页面数据绑定
     * @return
     */
    List<Map> getMgr();
}
