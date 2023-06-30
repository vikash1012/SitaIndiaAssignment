package com.sitaindia.backend.controller.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
// @AllArgsConstructor
@EqualsAndHashCode
public class EmployeeResponse {
    String empName;
    int amount;
    public EmployeeResponse(String empName,int currency){
        this.empName=empName;
        this.amount=currency;
    }
}
