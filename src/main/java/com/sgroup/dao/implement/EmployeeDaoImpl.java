package com.sgroup.dao.implement;

import com.sgroup.dao.EmployeeDao;
import com.sgroup.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl extends BaseDao implements EmployeeDao {
    /**
     * 添加员工
     * @param employee 要添加的员工
     * @return 返回添加执行的条目数，0为失败
     */
    @Override
    public int addEmployee(Employee employee) {
        int result = 0;
        String sql = "INSERT INTO employee(empID, emp_name) VALUES(?, ?)";
        Object[] objects = {employee.getEmpId(), employee.getEmpName()};
        result = super.executeUpdate(sql, objects);
        return result;
    }

    /**
     * 删除员工
     * @param employeeId 员工id
     * @return 返回修改执行的条目数，0为失败
     */
    @Override
    public int delEmployee(int employeeId) {
        int result = 0;
        String sql = "DELETE FROM employee WHERE empID = ?";
        Object[] objects = {employeeId};
        result = super.executeUpdate(sql,objects);
        return result;
    }

    /**
     * 修改员工
     * @param employee 要修改的员工
     * @return 返回修改的条目数，0为失败
     */
    @Override
    public int editEmployee(Employee employee) {
        int result = 0;
        String sql = "UPDATE employee SET emp_name = ? WHERE empID = ?";
        Object[] objects = {employee.getEmpName(), employee.getEmpId()};
        result = super.executeUpdate(sql, objects);
        return result;
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
        String sql = "SELECT * FROM employee LIMIT ?,?";
        Object[] objects = {start, limit};
        ResultSet resultSet = super.executeQuery(sql, objects);
        return getEmployeeList(resultSet);
    }

    /**
     * 查询员工总数，用于分页
     *
     * @return 员工总数
     */
    @Override
    public int queryEmployeeNum() {
        int result = 0;
        String sql = "SELECT COUNT(empID) FROM employee";
        Object[] objects = {};
        ResultSet resultSet = super.executeQuery(sql, objects);
        try {
            if(resultSet.next()) {
                result = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return result;
    }

    /**
     * 通过Id查询员工
     *
     * @param employeeId
     * @return
     */
    @Override
    public Employee queryEmployee(int employeeId) {
        String sql = "SELECT * FROM employee WHERE empID = ?";
        Object[] objects = {employeeId};
        ResultSet rs = super.executeQuery(sql,objects);
        return getOneEmployee(rs);
    }

    /**
     * 从结果集中获取员工列表
     * @param resultSet
     * @return
     */
    private List<Employee> getEmployeeList(ResultSet resultSet) {
        List<Employee> employees = new ArrayList<Employee>();
        try {
            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setEmpId(resultSet.getInt("empID"));
                employee.setEmpName(resultSet.getString("emp_name"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return employees;
    }



    /**
     * 从结果集中获取员工列表
     * @param resultSet
     * @return
     */
    private Employee getOneEmployee(ResultSet resultSet) {
        Employee employee = null;
        try {
            if (resultSet.next()) {
                employee = new Employee();
                employee.setEmpId(resultSet.getInt("empID"));
                employee.setEmpName(resultSet.getString("emp_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            super.closeAll();
        }
        return employee;
    }
}
