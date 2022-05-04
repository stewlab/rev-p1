package dev.thom.dao;

import dev.thom.entities.Expense;

import java.util.List;

public interface ExpenseDao {

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


    //- POST /expenses
    Expense addExpense(Expense expense);

    //- GET /expenses
    List<Expense> getExpenses();

    //- GET /expenses?status=pending
    List<Expense> getExpensesByStatus(String status);

    //- GET /expenses/12
    Expense getExpense(Integer expenseId);

    Expense updateExpense(Expense expense);

    //- DELETE /expenses/19
    Expense deleteExpense(Integer expenseId);

    List<Expense> getExpensesByEmployeeId(Integer employeeId);


}
