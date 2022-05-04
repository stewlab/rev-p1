package dev.thom.dao;

import dev.thom.entities.Employee;
import dev.thom.utilities.ConnectionUtil;
import dev.thom.utilities.ProjectUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDaoImpl implements EmployeeDao {

//    create table employee (
//            employee_id serial primary key,
//            first_name varchar(255),
//    last_name varchar(255),
//    create_date timestamp not null,
//    modify_date timestamp
//)
    public static final String INSERT_EMPLOYEE = "" +
        "INSERT INTO employee (first_name, last_name, create_date, modify_date) \n" +
        "VALUES (?, ?, current_date, current_date) \n";

    public static final String GET_EMPLOYEES = "" +
            "SELECT employee_id, first_name, last_name, create_date, modify_date \n" +
            "FROM employee \n";

    public static final String GET_EMPLOYEE = "" +
            "SELECT employee_id, first_name, last_name, create_date, modify_date \n" +
            "FROM employee \n" +
            "WHERE employee_id = ? \n";

    public static final String UPDATE_EMPLOYEE = "" +
            "UPDATE employee \n" +
            "SET first_name=?, last_name=?, modify_date=current_date \n" +
            "WHERE employee_id=? \n";

    public static final String DELETE_EMPLOYEE = "" +
            "DELETE FROM employee \n" +
            "WHERE employee_id = ? \n";

    @Override
    public Employee addEmployee(Employee employee) {
        Employee newEmployee = new Employee();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {

                newEmployee = ProjectUtil.buildEmployeeFromResultSet(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newEmployee;

    }

    @Override
    public List<Employee> getEmployees() {
        final List<Employee> newEmployeeList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEES);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                Employee employee = ProjectUtil.buildEmployeeFromResultSet(resultSet);
                newEmployeeList.add(employee);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return newEmployeeList;
    }

    @Override
    public Employee getEmployee(Integer employeeId) {
        Employee employee = new Employee();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOYEE);

            preparedStatement.setInt(1, employeeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                employee = ProjectUtil.buildEmployeeFromResultSet(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee updatedEmployee = new Employee();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOYEE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getEmployeeId());

            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                updatedEmployee = ProjectUtil.buildEmployeeFromResultSet(generatedKeys);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedEmployee;
    }

    @Override
    public Employee deleteEmployee(Integer employeeId) {
        Employee deletedEmployee = new Employee();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EMPLOYEE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, employeeId);

            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                deletedEmployee = ProjectUtil.buildEmployeeFromResultSet(generatedKeys);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletedEmployee;
    }

}
