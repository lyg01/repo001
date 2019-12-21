package com.aaa.sm.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * fileName:EmpDao
 * description:
 * author:zz
 * createTime:2019/11/13 11:27
 * version:1.0.0
 */
public interface EmpDao {


    /**
     * 根据参数查询列表
     * @param map
     * @return
     */

//    select empno,ENAME,job,mgr,hiredate,sal,comm,deptno from emp
    @Select("<script>" +
            "select a.empno,a.ename,a.job,a.mgr,to_char(a.hiredate,'yyyy-mm-dd') hiredate,a.sal,a.comm,a.deptno,a.mgrname,a.dname,a.rn from " +
            "(select b.*,rownum rn from " +
            "(select e.*,d.dname from (select e1.*,e2.ename mgrname from emp e1 left join emp e2 on e1.mgr=e2.empno) e left join dept d on e.deptno=d.deptno " +
            "<where>" +
            "<if test='ename!=null'>and e.ename like '%'||#{ename}||'%' </if>" +
            "<if test='job!=null'>and e.job like '%'||#{job}||'%' </if>" +
            "</where>" +
            "order by empno desc) b where rownum &lt; #{end}) a where a.rn &gt; #{start}" +
            "</script>")
    List<Map>  getList(Map map);


    /**
     * 带条件查询总条数
     * @param map
     * @return
     */
    @Select("<script>"+
            "select count(*) from emp <where>" +
            "<if test='ename != null'> and ename like '%${ename}%'</if>"+
            "<if test='job != null'> and job like '%${job}%'</if>"+
            "</where></script>")
    int getPageCount(Map map);
    /**
     * 添加
     * @param map
     * @return
     */
//    seq_emp_id.nextval,ename, job,  hiredate, sal, comm,deptno
    @Insert("insert into emp values(seq_emp_id.nextval,#{ENAME},#{JOB},#{MGR},to_date(#{HIREDATE},'yyyy-mm-dd'),#{SAL},#{COMM},#{DEPTNO})")
    int add(Map map);


    /**
     * 更新
     * @param map
     * @return
     */
    @Update("update emp set ename=#{ENAME},job=#{JOB},mgr=#{MGR},hiredate=to_date(#{HIREDATE},'yyyy-mm-dd'),sal=#{SAL},comm=#{COMM},deptno=#{DEPTNO} where empno=#{EMPNO}")
    int update(Map map);

    /**
     * 删除
     * @param deptNo
     * @return
     */
    @Delete("delete from emp where empno=#{EMPNO}")
    int delete(int deptNo);

    /**
     * 获取所有部门，用于页面数据绑定
     * @return
     */
    @Select("select deptno,dname from dept")
    List<Map> getDeptType();

    /**
     * 获取所有职位，用于页面数据绑定
     * @return
     */
    @Select("select distinct(job) from emp")
    List<Map> getJob();

    /**
     * 获取上司，用于页面数据绑定
     * @return
     */
    @Select("select empno,ename from emp where job='PRESIDENT' or job='MANAGER' or job='ANALYST'")
    List<Map> getMgr();


}
