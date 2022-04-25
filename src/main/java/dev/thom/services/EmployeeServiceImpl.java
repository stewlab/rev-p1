package dev.thom.services;

import dev.thom.dao.EmployeeDao;
import dev.thom.entities.Employee;

import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public Employee addEmployee(Employee employee) {
        return this.employeeDao.addEmployee(employee);
    }

    @Override
    public List<Employee> getEmployees() {
        return this.employeeDao.getEmployees();
    }

    @Override
    public Employee getEmployee(Integer employeeId) {
        return this.employeeDao.getEmployee(employeeId);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return this.employeeDao.updateEmployee(employee);
    }

    @Override
    public Employee deleteEmployee(Integer employeeId) {
        return this.employeeDao.deleteEmployee(employeeId);
    }
}
