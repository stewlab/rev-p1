package dev.thom.dao;

import dev.thom.entities.Employee;
import dev.thom.entities.Expense;
import dev.thom.utilities.ConnectionUtil;
import dev.thom.utilities.ProjectUtil;
import dev.thom.utilities.Statuses;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExpenseDaoImpl implements ExpenseDao {

    //### Expenses routes
//- POST /expenses
//  - returns a 201
//            - GET /expenses
//- GET /expenses?status=pending
//  - also can get status approved or denied
//- GET /expenses/12
//            - returns a 404 if expense not found
//- PUT /expenses/15
//            - returns a 404 if expense not found
//- PATCH /expenses/20/approve
//  - returns a 404 if expense not found
//- PATCH /expenses/20/deny
//  - returns a 404 if expense not found
//- DELETE /expenses/19
//            - returns a 404 if car not found
//
//    It is common for REST routes to be nested
//- GET /employees/120/expenses
//  - returns expenses for employee 120
//            - POST /employees/120/expenses
//  - adds an expense to employee 120


    public static final String INSERT_EXPENSE = "" +
            "INSERT INTO expense (expense_status, employee_id, amount, create_date) \n" +
            "VALUES (?, ?, ?, current_date) \n";

    public static final String UPDATE_EXPENSE = "" +
            "UPDATE expense\n" +
            "SET expense_status = ?, employee_id = ?, amount = ?, modify_date = current_date\n" +
            "WHERE expense_id = ?\n";

    public static final String DELETE_EXPENSE = "" +
            "DELETE FROM expense \n" +
            "WHERE expense_id = ? \n";

    public static final String GET_EXPENSE_BY_ID = "" +
            "SELECT expense_id, expense_status, employee_id, amount, create_date, modify_date\n" +
            "FROM expense e\n" +
            "WHERE e.expense_id = ?\n";

    public static final String GET_EXPENSES= "" +
            "SELECT expense_id, expense_status, employee_id, amount, create_date, modify_date\n" +
            "FROM expense e\n";

    public static final String GET_EXPENSES_BY_STATUS = "" +
            "SELECT expense_id, expense_status, employee_id, amount, create_date, modify_date\n" +
            "FROM expense e\n" +
            "WHERE e.expense_status = ?\n";

    public static final String GET_EXPENSES_BY_EMPLOYEE_ID = "" +
            "SELECT expense_id, expense_status, employee_id, amount, create_date, modify_date\n" +
            "FROM expense e\n" +
            "WHERE e.employee_id = ?\n";

    @Override
    public Expense addExpense(Expense expense) {

        Expense newExpense = new Expense();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EXPENSE,
                    PreparedStatement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, Statuses.PENDING);
            preparedStatement.setInt(2, expense.getEmployeeId());
            preparedStatement.setDouble(3, expense.getAmount());

            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {

                newExpense = ProjectUtil.buildExpenseFromResultSet(resultSet);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return newExpense;
    }

    @Override
    public List<Expense> getExpenses() {
        List<Expense> expenseList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPENSES);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Expense expense = ProjectUtil.buildExpenseFromResultSet(resultSet);
                if (expense != null) {
                    expenseList.add(expense);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenseList;
    }

    @Override
    public List<Expense> getExpensesByStatus(String status) {
        List<Expense> expenseList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPENSES_BY_STATUS);
            preparedStatement.setString(1, status);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Expense expense = ProjectUtil.buildExpenseFromResultSet(resultSet);
                if (expense != null) {
                    expenseList.add(expense);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenseList;
    }

    @Override
    public Expense getExpense(Integer expenseId) {
        Expense expense = new Expense();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPENSE_BY_ID);
            preparedStatement.setInt(1, expenseId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                expense = ProjectUtil.buildExpenseFromResultSet(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expense;

    }

    @Override
    public Expense updateExpense(Expense expense) {
        Expense updatedExpense = new Expense();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EXPENSE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, expense.getExpenseStatus());
            preparedStatement.setInt(2, expense.getEmployeeId());
            preparedStatement.setDouble(3, expense.getAmount());
            preparedStatement.setInt(4, expense.getExpenseId());

            preparedStatement.execute();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

            if (generatedKeys.next()) {
                updatedExpense = ProjectUtil.buildExpenseFromResultSet(generatedKeys);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updatedExpense;
    }

    @Override
    public Expense deleteExpense(Integer expenseId) {

        Expense deletedExpense = new Expense();

        try (Connection connection = ConnectionUtil.createConnection(null)) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_EXPENSE,
                    Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, expenseId);

            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();

            if (rs.next()) {
                deletedExpense.setExpenseId(expenseId);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletedExpense;
    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        List<Expense> expenseList = new ArrayList<>();

        try (Connection connection = ConnectionUtil.createConnection(null)) {

            PreparedStatement preparedStatement = connection.prepareStatement(GET_EXPENSES_BY_EMPLOYEE_ID);
            preparedStatement.setInt(1, employeeId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Expense expense = ProjectUtil.buildExpenseFromResultSet(resultSet);
                if (expense != null) {
                    expenseList.add(expense);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return expenseList;
    }

}
