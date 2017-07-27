package com.shengdiyage.dao;

import com.shengdiyage.entity.Employee;

import java.util.List;

/**
 * Created by Akari on 2017/6/27.
 */
public interface EmployeeDao {
    /**
     * 添加员工
     * @param employee 要添加的员工
     * @return 返回添加执行的条目数，0为失败
     */
    int addEmployee(Employee employee);

    /**
     * 删除员工
     * @param employeeId 员工id
     * @return 返回修改的条目数，0为失败
     */
    int delEmployee(int employeeId);

    /**
     * 修改员工
     * @param employee 要修改的员工
     * @return 返回修改执行的条目数，0为失败
     */
    int editEmployee(Employee employee);

    /**
     * 分页查询员工
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的员工列表
     */
    List<Employee> queryEmployee(int start, int limit);

    /**
     * 查询员工总数，用于分页
     * @return 员工总数
     */
    int queryEmployeeNum();

    /**
     * 通过Id查询员工
     * @param employeeId
     * @return
     */
    Employee queryEmployee(int employeeId);
}
