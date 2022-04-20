package dev.thom.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {

    private Integer expenseId;
    private String expenseStatus;
    private Integer employeeId;
    private Double amount;
    private Long createDate;
    private Long modifyDate;

}
