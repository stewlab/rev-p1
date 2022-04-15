package dev.thom.dao;

import dev.thom.entities.Employee;

import java.util.List;

public interface EmployeeDao {

//    POST /employees
    void addEmployee(Employee employee);

//    GET /employees
    List<Employee> getEmployees();

//    GET /employees/120
    Employee getEmployee();

//    PUT /employees/150
    void updateEmployee(Employee employee);

//    DELETE /employees/190
    void deleteEmployee(Integer employeeId);
}
