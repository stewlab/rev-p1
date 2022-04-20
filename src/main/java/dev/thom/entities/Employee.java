package dev.thom.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    private Integer employee_id;
    private String firstName;
    private String lastName;
    private Long createDate;
    private Long modifyDate;

}
