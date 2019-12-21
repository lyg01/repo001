package com.aaa.sm.entity;

/**
 * fileName:Dept
 * description:
 * author:zz
 * createTime:2019/11/14 9:48
 * version:1.0.0
 */
public class Dept {

    private String deptName;

    public Dept() {
    }

    public Dept(String deptName) {

        this.deptName = deptName;
    }

    public void printDept(){
        System.out.println(deptName);
    }
}
