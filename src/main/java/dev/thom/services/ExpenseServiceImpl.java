package dev.thom.services;

import dev.thom.dao.ExpenseDao;
import dev.thom.entities.Expense;
import dev.thom.utilities.Statuses;

import java.util.List;

public class ExpenseServiceImpl implements ExpenseService {

    private ExpenseDao expenseDao;

    public ExpenseServiceImpl(ExpenseDao expenseDao) {
        this.expenseDao = expenseDao;
    }

    @Override
    public Expense addExpense(Expense expense) {
        return this.expenseDao.addExpense(expense);
    }

    @Override
    public List<Expense> getExpenses() {
        return this.expenseDao.getExpenses();
    }

    @Override
    public List<Expense> getExpenseByStatus(String status) {
        return this.expenseDao.getExpensesByStatus(status);
    }

    @Override
    public Expense getExpense(Integer expenseId) {
        return this.expenseDao.getExpense(expenseId);
    }

    @Override
    public Expense updateExpense(Expense expense) {

        Expense updatedExpense = new Expense();

        Expense expenseTemp = getExpense(expense.getExpenseId());

        if (expenseTemp != null && Statuses.PENDING.equalsIgnoreCase(expenseTemp.getExpenseStatus())) {
            updatedExpense = this.expenseDao.updateExpense(expense);
        }

        return updatedExpense;
    }

    @Override
    public Expense approveExpense(Integer expenseId) {

        Expense updatedExpense = new Expense();

        Expense expense = getExpense(expenseId);

        if (expense != null && Statuses.PENDING.equalsIgnoreCase(expense.getExpenseStatus())) {
            expense.setExpenseStatus(Statuses.APPROVED);
            updatedExpense = this.expenseDao.updateExpense(expense);
        }

        return updatedExpense;

    }

    @Override
    public Expense denyExpense(Integer expenseId) {

        Expense updatedExpense = new Expense();

        Expense expense = getExpense(expenseId);

        if (expense != null && Statuses.PENDING.equalsIgnoreCase(expense.getExpenseStatus())) {
            expense.setExpenseStatus(Statuses.DENIED);
            updatedExpense = this.expenseDao.updateExpense(expense);
        }

        return updatedExpense;

    }

    @Override
    public Expense deleteExpense(Integer expenseId) {
        return this.expenseDao.deleteExpense(expenseId);
    }

    @Override
    public List<Expense> getExpensesByEmployeeId(Integer employeeId) {
        return this.expenseDao.getExpensesByEmployeeId(employeeId);
    }

}
