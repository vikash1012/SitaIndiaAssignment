package com.sitaindia.backend.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
// @AllArgsConstructor
public class EmployeeResponse {
    String empName;
    int currency;
    public EmployeeResponse(String empName,int currency){
        this.empName=empName;
        this.currency=currency;
    }
}
