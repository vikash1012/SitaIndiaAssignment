package com.sitaindia.backend.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class EmployeeRequest{
    private String empName;
    private int amount;
    private String currency;
    private String joiningDate;
    private String exitDate;
    private String department; 
}
