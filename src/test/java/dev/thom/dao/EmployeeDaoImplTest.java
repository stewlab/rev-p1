package dev.thom.dao;

import dev.thom.entities.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoImplTest {

    static EmployeeDao employeeDao = new EmployeeDaoImpl();
    Employee testEmployee = new Employee();

    static String FIRST_NAME = "Thom";
    static String LAST_NAME = "Bell";
    static Long CREATE_DATE = 123456788L;
    static Long MODIFY_DATE = 123456789L;

    @Test
    void testAddEmployee() {
        testEmployee.setFirstName(FIRST_NAME);
        testEmployee.setLastName(LAST_NAME);
        testEmployee.setCreateDate(CREATE_DATE);
        testEmployee.setModifyDate(MODIFY_DATE);

        Employee newEmployee = employeeDao.addEmployee(testEmployee);

        Assertions.assertTrue(newEmployee.getEmployeeId() != 0);
    }

    @Test
    void getEmployees() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}