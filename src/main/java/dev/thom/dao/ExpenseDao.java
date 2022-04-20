package dev.thom.dao;

import dev.thom.entities.Expense;

import java.util.List;

public interface ExpenseDao {

    Expense addExpense(Expense expense);

    List<Expense> getExpenseByStatus(String status);

    Expense getExpense(Integer expenseId);

    Expense updateExpense(Expense expense);

    Expense deleteExpense(Integer expenseId);

    List<Expense> getExpensesByEmployeeId(Integer employeeId);

    Expense addExpenseById(Expense expense, Integer employeeId);

}
