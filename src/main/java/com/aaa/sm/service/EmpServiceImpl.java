package com.aaa.sm.service;

import com.aaa.sm.dao.EmpDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * fileName:DeptServiceImpl
 * description:
 * author:zz
 * createTime:2019/11/13 11:32
 * version:1.0.0
 */
@Service
@Transactional
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpDao empDao;

    @Override
    public List<Map> getList(Map map) {
        int pageNo = Integer.valueOf(map.get("pageNo")+"");
        int pageSize = Integer.valueOf(map.get("pageSize")+"");

        map.put("start",(pageNo-1)*pageSize);
        map.put("end",pageNo*pageSize+1);
        return empDao.getList(map);
    }

    @Override
    public int getPageCount(Map map) {
        return empDao.getPageCount(map);
    }

    @Override
    public int add(Map map) {
       // System.out.println(1/0);
        return  empDao.add(map);
    }

    @Override
    public int update(Map map) {
        return empDao.update(map);
    }

    @Override
    public int delete(int deptNo) {
        return empDao.delete(deptNo);
    }

    @Override
    public List<Map> getDeptType() {
        return empDao.getDeptType();
    }

    @Override
    public List<Map> getJob() {
        return empDao.getJob();
    }

    @Override
    public List<Map> getMgr() {
        return empDao.getMgr();
    }
}
