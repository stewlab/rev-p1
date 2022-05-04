package dev.thom.api;

import com.google.gson.Gson;
import dev.thom.dao.EmployeeDaoImpl;
import dev.thom.dao.ExpenseDaoImpl;
import dev.thom.entities.Employee;
import dev.thom.entities.Expense;
import dev.thom.services.EmployeeService;
import dev.thom.services.EmployeeServiceImpl;
import dev.thom.services.ExpenseService;
import dev.thom.services.ExpenseServiceImpl;
import io.javalin.Javalin;

import java.util.ArrayList;
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
//- GET /expenses
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

        EmployeeService employeeService = new EmployeeServiceImpl(new EmployeeDaoImpl());
        ExpenseService expenseService = new ExpenseServiceImpl(new ExpenseDaoImpl());

        Javalin javalinApp = Javalin.create();

        javalinApp.get("/", context -> {
            context.result("Employee Expense app started!!!");
            context.status(200);
        });

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


        // POST /employees
        javalinApp.post("/employees", context -> {

            String body = context.body();
            Gson gson = new Gson();
            String response = "";

            Employee employeeRequest = gson.fromJson(body, Employee.class);

            Employee employeeResponse = employeeService.addEmployee(employeeRequest);

            if (employeeResponse != null && employeeResponse.getEmployeeId() != null) {
                response = gson.toJson(employeeResponse);
                context.status(201);
            } else {
                response = "Employee not found";
                context.status(404);
            }

            context.result(response);

        });

        // GET /employees
        javalinApp.get("/employees", context -> {

            Gson gson = new Gson();

            List<Employee> employeeList = employeeService.getEmployees();
            String response = gson.toJson(employeeList);

            context.status(201);
            context.result(response);

        });

        // GET /employees/120
        javalinApp.get("/employees/{id}", context -> {

            String response = "";
            Gson gson = new Gson();

            Integer employeeId = Integer.valueOf(context.pathParam("id"));

            Employee employee = employeeService.getEmployee(employeeId);

            if (employee != null && employee.getEmployeeId() != null) {
                response = gson.toJson(employee);
                context.status(201);
            } else {
                response = "Employee not found";
                context.status(404);
            }

            context.result(response);

        });

        //PUT /employees/150
        javalinApp.put("/employees/{id}", context -> {

            String response = "";
            Gson gson = new Gson();

            String body = context.body();

            Employee employeeRequest = gson.fromJson(body, Employee.class);
            Integer employeeId = Integer.valueOf(context.pathParam("id"));

            if (employeeId != null) {
                employeeRequest.setEmployeeId(employeeId);
            }

            Employee employeeResponse = employeeService.updateEmployee(employeeRequest);

            if (employeeResponse.getEmployeeId() != null) {
                response = gson.toJson(employeeResponse);
                context.status(201);
            } else {
                response = "Employee not found";
                context.status(404);
            }

            context.result(response);

        });

//        DELETE /employees/190
        javalinApp.delete("/employees/{id}", context -> {

            String response = "";

            Integer employeeId = Integer.valueOf(context.pathParam("id"));

            Employee employeeResponse = employeeService.deleteEmployee(employeeId);

            if (employeeResponse.getEmployeeId() != null) {
                response = "Delete successful";
                context.status(201);
            } else {
                response = "Employee not found";
                context.status(404);
            }

            context.result(response);

        });

        //### Expenses routes
//- POST /expenses
//  - returns a 201
//- GET /expenses
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
//  - POST /employees/120/expenses
//  - adds an expense to employee 120


        //- POST /expenses
        javalinApp.post("/expenses", context -> {

            String response = "";

            Gson gson = new Gson();

            String body = context.body();

            Expense expenseRequest = gson.fromJson(body, Expense.class);

            Expense expenseResponse = expenseService.addExpense(expenseRequest);

            if (expenseResponse != null && expenseResponse.getExpenseId() != null) {
                context.status(201);
                response = gson.toJson(expenseResponse);
            } else {
                context.status(500);
                response = "Expense not added!";

            }


            context.result(response);

        });

        //- GET /expenses
        //- GET /expenses?status=pending
        javalinApp.get("/expenses", context -> {

            Gson gson = new Gson();

            List<Expense> expenseList = new ArrayList<>();

            String expenseStatus = context.queryParam("status");

            if (expenseStatus != null) {

                expenseList = expenseService.getExpenseByStatus(expenseStatus);

            } else {

                expenseList = expenseService.getExpenses();

            }

            String response = gson.toJson(expenseList);

            context.status(201);
            context.result(response);

        });

        //- GET /expenses/12
        javalinApp.get("/expenses/{id}", context -> {

            Gson gson = new Gson();
            String response = "";

            Integer expenseId = Integer.valueOf(context.pathParam("id"));

            Expense expenseResponse = expenseService.getExpense(expenseId);

            if (expenseResponse != null && expenseResponse.getExpenseId() != null) {

                response = gson.toJson(expenseResponse);
                context.status(201);

            } else {
                response = "Expense not found";
                context.status(404);
            }

            context.result(response);

        });

        //- PUT /expenses/15
        javalinApp.put("/expenses/{id}", context -> {

            Gson gson = new Gson();
            String response = "";

            Integer expenseId = Integer.valueOf(context.pathParam("id"));

            String body = context.body();
            Expense expenseRequest = gson.fromJson(body, Expense.class);
            expenseRequest.setExpenseId(expenseId);


            if (expenseRequest != null &&
                    (expenseRequest.getExpenseStatus() == null
                            || expenseRequest.getEmployeeId() == null
                            || expenseRequest.getAmount() == null)) {

                response = "Bad Expense request :(";
                context.status(400);

            } else if (expenseRequest != null) {

                Expense expenseResponse = expenseService.updateExpense(expenseRequest);

                if (expenseResponse != null && expenseResponse.getExpenseId() != null) {

                    response = gson.toJson(expenseResponse);
                    context.status(201);

                } else {

                    response = "Expense not found";
                    context.status(404);

                }


            }

            context.result(response);

        });

        //- PATCH /expenses/20/approve
        javalinApp.patch("/expenses/{id}/approve", context -> {

            Gson gson = new Gson();
            String response = "";

            Integer expenseId = Integer.valueOf(context.pathParam("id"));

            Expense expenseResponse = expenseService.approveExpense(expenseId);

            if (expenseResponse != null && expenseResponse.getExpenseId() != null) {
                response = gson.toJson(expenseResponse);
                context.status(201);
            } else {
                response = "Expense not found";
                context.status(404);
            }

            context.result(response);

        });

        //- PATCH /expenses/20/deny
        javalinApp.patch("/expenses/{id}/deny", context -> {

            Gson gson = new Gson();
            String response = "";

            Integer expenseId = Integer.valueOf(context.pathParam("id"));

            Expense expenseResponse = expenseService.denyExpense(expenseId);

            if (expenseResponse != null && expenseResponse.getExpenseId() != null) {
                response = gson.toJson(expenseResponse);
                context.status(201);
            } else {
                response = "Expense not found";
                context.status(404);
            }

            context.result(response);

        });

        //- DELETE /expenses/19
        javalinApp.delete("/expenses/{id}", context -> {

            Gson gson = new Gson();
            String response = "";

            Integer expenseId = Integer.valueOf(context.pathParam("id"));

            Expense expenseResponse = expenseService.deleteExpense(expenseId);

            if (expenseResponse != null && expenseResponse.getExpenseId() != null) {
//                response = "Expense delete successful";
                response = gson.toJson(expenseResponse);
                context.status(201);
            } else {
                response = "Expense not found";
                context.status(404);
            }

            context.result(response);

        });

        //- GET /employees/120/expenses

        javalinApp.get("/employees/{id}/expenses", context -> {

            Gson gson = new Gson();

            List<Expense> expenseList = new ArrayList<>();

            Integer employeeId = Integer.valueOf(context.pathParam("id"));

            expenseList = expenseService.getExpensesByEmployeeId(employeeId);

            String response = gson.toJson(expenseList);

            context.status(201);
            context.result(response);

        });


//  - POST /employees/120/expenses
//  - adds an expense to employee 120

        javalinApp.post("/employees/{id}/expenses", context -> {

            String response = "";

            Gson gson = new Gson();

            String body = context.body();

            Expense expenseRequest = gson.fromJson(body, Expense.class);
            Integer employeeId = Integer.valueOf(context.pathParam("id"));

            expenseRequest.setEmployeeId(employeeId);

            Expense expenseResponse = expenseService.addExpense(expenseRequest);

            if (expenseResponse != null && expenseResponse.getExpenseId() != null) {
                context.status(201);
                response = gson.toJson(expenseResponse);
            } else {
                context.status(500);
                response = "Expense not added!";

            }


            context.result(response);

        });


        javalinApp.start(5000);

    }

}
