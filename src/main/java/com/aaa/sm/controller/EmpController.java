package com.aaa.sm.controller;

import com.aaa.sm.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * fileName:DeptController
 * description:
 * author:zz
 * createTime:2019/11/13 11:34
 * version:1.0.0
 */
@RestController
@RequestMapping("emp")
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 列表
     * @param map
     * @return
     */
    @RequestMapping("list")
    public Object list(@RequestParam Map map){
       /* System.out.println(map.get("pageNo"));
        System.out.println(map.get("pageSize"));*/
        Map resultMap = new HashMap();
        resultMap.put("list",empService.getList(map));
        resultMap.put("total",empService.getPageCount(map));
        //部门类别列表
        resultMap.put("deptType",empService.getDeptType());
        //职位列表
        resultMap.put("allJob",empService.getJob());
        //上司列表
        resultMap.put("allMgr",empService.getMgr());
        return resultMap;
    }

    /**
     * 添加
     * @param map
     * @return
     */
    @RequestMapping("add")
    public Object add(@RequestBody Map map){
        return  empService.add(map);
    }
    /**
     * 修改
     * @param map
     * @return
     */
    @RequestMapping("update")
    public Object update(@RequestBody Map map){
        /*System.out.println(map+"............");
        return deptService.update(map);*/   //有bug
        try {
            empService.update(map);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    /**
     * 删除
     * @param empNo
     * @return
     */
    @RequestMapping("delete")
    public Object delete(Integer empNo){
        return empService.delete(empNo);
    }
}
