package dev.thom.dao;

import dev.thom.entities.Employee;

import java.util.List;

public interface EmployeeDao {

//    POST /employees
    Employee addEmployee(Employee employee);

//    GET /employees
    List<Employee> getEmployees();

//    GET /employees/120
    Employee getEmployee();

//    PUT /employees/150
    Employee updateEmployee(Employee employee);

//    DELETE /employees/190
    Employee deleteEmployee(Integer employeeId);
}
