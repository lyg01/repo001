package com.aaa.sm.service;

import com.aaa.sm.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * fileName:UserServiceImpl
 * description:
 * author:zz
 * createTime:2019/11/12 10:44
 * version:1.0.0
 */
@Service
public class UserServiceImpl implements  UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<Map> getListByParam(Map map) {
        return userDao.getListByParam(map);
    }

    @Override
    public List<Map> getRoleListByUserName(String userName) {
        return userDao.getRoleListByUserName(userName);
    }
}
