package dev.thom.utilities;

import dev.thom.entities.Employee;
import dev.thom.entities.Expense;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProjectUtil {

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

    public static Expense buildExpenseFromResultSet(ResultSet resultSet) throws SQLException {
        Expense expense = new Expense();

        if (resultSet != null) {
            Integer expenseId = resultSet.getInt("expense_id");
            String expenseStatus = resultSet.getString("expense_status");
            Integer employeeId = resultSet.getInt("employee_id");
            Double amount = resultSet.getDouble("amount");
            Timestamp createDate = resultSet.getTimestamp("create_date");
            Timestamp modifyDate = resultSet.getTimestamp("modify_date");


            expense.setExpenseId(expenseId);
            expense.setExpenseStatus(expenseStatus);
            expense.setEmployeeId(employeeId);
            expense.setAmount(amount);

            if (createDate != null) {
                expense.setCreateDate(createDate.getTime());
            }

            if (modifyDate != null) {
                expense.setModifyDate(modifyDate.getTime());
            }


        }

        return expense;

    }

}
