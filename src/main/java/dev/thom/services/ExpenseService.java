package dev.thom.services;

import dev.thom.entities.Expense;

import java.util.List;

public interface ExpenseService {

    //    POST /expenses
    void addExpense(Expense expense);

    //    GET /expenses?status=pending
    List<Expense> getExpenseByStatus(String status);

    //    GET /expenses/12
    Expense getExpense(Integer expenseId);

    //    PUT /expenses/15
    void updateExpense(Expense expense);

    //    PATCH /expenses/20/approve
    void approveExpense(Integer expenseId);

    //    PATCH /expenses/20/deny
    void denyExpense(Integer expenseId);

    //    DELETE/expenses/19
    void deleteExpense(Integer expenseId);

    //    GET /employees/120/expenses
    List<Expense> getExpensesByEmployeeId(Integer employeeId);

    //    POST /employees/120/expenses
    void addExpenseByEmployeeId(Expense expense, Integer employeeId);

}
