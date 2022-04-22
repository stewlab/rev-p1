package dev.thom.api;

import com.google.gson.Gson;
import dev.thom.entities.Employee;
import dev.thom.services.EmployeeService;
import dev.thom.services.EmployeeServiceImpl;
import dev.thom.services.ExpenseService;
import dev.thom.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.List;

public class App {

//    ### Employee Routes
//- POST /employees
//  - returns a 201
// - GET /employees
//- GET /employees/120
//            - returns a 404 if employee not found
//- PUT /employees/150
//            - returns a 404 if employee not found
//- DELETE /employees/190
//            - returns a 404 if employee not found
//
//
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

    public static void main(String[] args) {

        ExpenseService expenseService = new ExpenseServiceImpl();
        EmployeeService employeeService = new EmployeeServiceImpl();

        Javalin javalinApp = Javalin.create();

        javalinApp.get("/", context -> {
            context.result("Employee Expense app started!!!");
            context.status(200);
        });

        // POST /employees
        javalinApp.post("/employees", context -> {

            String body = context.body();
            Gson gson = new Gson();

            Employee employee = gson.fromJson(body, Employee.class);

            employeeService.addEmployee(employee);

            context.status(201);
            context.result("Added employee!!!");

        });

        javalinApp.start(5000);

    }

}
