package dev.thom.dao;

import dev.thom.entities.Employee;
import dev.thom.utilities.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

                newEmployee = buildEmployeeFromResultSet(resultSet);

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

                Employee employee = buildEmployeeFromResultSet(resultSet);
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

                employee = buildEmployeeFromResultSet(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employee;
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return null;
    }

    @Override
    public Employee deleteEmployee(Integer employeeId) {
        return null;
    }

    private static Employee buildEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();

        if (resultSet != null) {
            Integer employeeId = resultSet.getInt("employee_id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            Long createDate = resultSet.getTimestamp("create_date").getTime();
            Long modifyDate = resultSet.getTimestamp("modify_date").getTime();


            employee.setEmployeeId(employeeId);
            employee.setFirstName(firstName);
            employee.setLastName(lastName);
            employee.setCreateDate(createDate);
            employee.setModifyDate(modifyDate);
        }


        return employee;

    }
}
