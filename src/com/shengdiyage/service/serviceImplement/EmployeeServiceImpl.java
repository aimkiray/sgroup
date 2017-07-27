package com.shengdiyage.service.serviceImplement;

import com.shengdiyage.dao.EmployeeDao;
import com.shengdiyage.dao.implement.EmployeeDaoImpl;
import com.shengdiyage.entity.Employee;
import com.shengdiyage.service.EmployeeService;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeDao employeeDao = new EmployeeDaoImpl();
    /**
     * 添加员工
     *
     * @param employee 要添加的员工
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    /**
     * 删除员工
     *
     * @param employeeId 员工id
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int delEmployee(int employeeId) {
        return employeeDao.delEmployee(employeeId);
    }

    /**
     * 修改员工
     *
     * @param employee 要修改的员工
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int editEmployee(Employee employee) {
        return employeeDao.editEmployee(employee);
    }

    /**
     * 分页查询员工
     *
     * @param start 从哪条开始查询
     * @param limit 一共查询多少条
     * @return 查到的员工列表
     */
    @Override
    public List<Employee> queryEmployee(int start, int limit) {
        return employeeDao.queryEmployee(start, limit);
    }

    /**
     * 查询员工总数，用于分页
     *
     * @return 员工总数
     */
    @Override
    public int queryEmployeeNum() {
        return employeeDao.queryEmployeeNum();
    }

    /**
     * 通过Id查询员工
     *
     * @param employeeId
     * @return
     */
    @Override
    public Employee queryEmployee(int employeeId) {
        return employeeDao.queryEmployee(employeeId);
    }
}
