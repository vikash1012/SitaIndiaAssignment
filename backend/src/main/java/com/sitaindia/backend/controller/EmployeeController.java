package com.sitaindia.backend.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sitaindia.backend.controller.dto.CreateEmployeeRequest;
import com.sitaindia.backend.controller.dto.GetAllEmployeeResponse;
import com.sitaindia.backend.service.EmployeeService;


@RestController
public class EmployeeController {
    
    
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService=employeeService;
    }

    @PostMapping("tci/employee-bonus")
    @ResponseStatus(HttpStatus.CREATED)
    public void createEmployee(@RequestBody CreateEmployeeRequest employees){
       
        this.employeeService.create(employees); 
    }
    
    @GetMapping("tci/employee-bonus")
    @ResponseStatus(HttpStatus.OK)
    public GetAllEmployeeResponse getAllEmployee(@RequestParam("date") String date){
       
        return this.employeeService.getEmployee(date);
    }

}

