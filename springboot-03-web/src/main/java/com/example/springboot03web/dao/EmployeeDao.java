package com.example.springboot03web.dao;

import com.example.springboot03web.pojo.Department;
import com.example.springboot03web.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 初始化数据，模拟数据库数据
     */
    static {
        employees = new HashMap<>();

        employees.put(1001, new Employee(1001, "AAA", "aaa@qq.com", 1, new Department(105, "后勤部")));
        employees.put(1002, new Employee(1002, "BBB", "bbb@qq.com", 1, new Department(102, "市场部")));
        employees.put(1003, new Employee(1003, "CCC", "ccc@qq.com", 0, new Department(105, "后勤部")));
        employees.put(1004, new Employee(1004, "DDD", "ddd@qq.com", 0, new Department(104, "运营部")));
        employees.put(1005, new Employee(1005, "EEE", "eee@qq.com", 1, new Department(101, "教学部")));
    }

    //主键自增
    private static Integer initId = 1006;

    /**
     * 添加员工
     *
     * @param employee
     */
    public void addEmployee(Employee employee) {
        //员工id为null时，使用默认主键
        if (employee.getId() == null) {
            employee.setId(initId++);
        }
        //传入部门id，自动设置所在部门名称（因为你在前端页面时，只需要设置你所在的部门id即可，而不是id和部门名称都需要手动填入）
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        employees.put(employee.getId(), employee);
    }

    /**
     * 获得所有员工信息
     * @return
     */
    public Collection<Employee> getAll() {
        return employees.values();
    }

    /**
     * 通过ID查询员工
     * @param id
     * @return
     */
    public Employee getEmployee(Integer id){
        return employees.get(id);
    }

    /**
     * 通过ID删除员工
     * @param id
     */
    public void removeEmployee(Integer id){
        employees.remove(id);
    }

    /**
     * 修改员工
     * @param employee
     */
    public void updateEmployee(Employee employee){
        //前端传进来的部门只有部门id，没有部门名称，所以要通过部门id设置部门名称
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));
        //通过ID替换员工
        employees.replace(employee.getId(),employee);
    }
}
