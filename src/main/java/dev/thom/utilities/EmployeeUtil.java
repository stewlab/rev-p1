package dev.thom.utilities;

import dev.thom.entities.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeUtil {

    public static Employee buildEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
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
