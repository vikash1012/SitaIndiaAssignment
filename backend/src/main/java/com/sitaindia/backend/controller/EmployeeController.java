package com.sitaindia.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sitaindia.backend.controller.dto.CreateEmployeeRequest;
import com.sitaindia.backend.service.EmployeeService;

@RestController
public class EmployeeController {
    
    
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping("tci/employee-bonus")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody List<CreateEmployeeRequest> employees){
       
        this.employeeService.create(employees);
       
        
    }
       
}
