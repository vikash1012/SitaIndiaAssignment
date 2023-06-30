package com.sitaindia.backend.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sitaindia.backend.controller.dto.CreateEmployeeRequest;
import com.sitaindia.backend.controller.dto.CreateEmployeeResponse;
import com.sitaindia.backend.controller.dto.EmployeeRequest;
import com.sitaindia.backend.controller.dto.EmployeeResponse;
import com.sitaindia.backend.controller.dto.GetAllEmployeeResponse;
import com.sitaindia.backend.model.Department;
import com.sitaindia.backend.model.Employee;
import com.sitaindia.backend.repository.EmployeeRepository;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest{

    EmployeeService employeeService;

    @Mock
    EmployeeRepository employeeRepository;


    @BeforeEach
    void init(){
        employeeService=new EmployeeService(employeeRepository);
    }
    @Test
    void shouldCreateEmployee(){
        CreateEmployeeRequest createEmployeeRequest=new CreateEmployeeRequest(List.of(new EmployeeRequest("Vikash",5000,"IR","June-23-2000","may-12-2090","it")));
         Date exitDate=Date.valueOf("2090-05-12");
        Date joinDate=Date.valueOf("2000-06-23");
        Employee employee = createEmployee(joinDate, exitDate);
        Department department=new Department();
        department.setDepartment("it");
        employee.setDepartment(department);
        List<Employee> employees=List.of(employee);
        doNothing().when(employeeRepository).create(any());

        this.employeeService.create(createEmployeeRequest);

        verify(employeeRepository,times(1)).create(any());


    }
    
    @Test
    void shoulReturnAllEmployeeWhichAreEligibleToTakeBonusOnGivenDate(){
         String date="dec-10-2001";
         List<EmployeeResponse> employeeResponses= List.of(new EmployeeResponse("vikash", 10000));
         List<CreateEmployeeResponse> createEmployeeResponses=List.of(new CreateEmployeeResponse("INR", employeeResponses));
         GetAllEmployeeResponse expected=new GetAllEmployeeResponse(createEmployeeResponses);
         when(this.employeeRepository.getAllEmployee(Date.valueOf("2001-12-10"))).thenReturn(createEmployeeResponses);

         GetAllEmployeeResponse actual=this.employeeService.getEmployee(date);

         assertEquals(expected,actual);
       
    }
    private Employee createEmployee(Date joinDate, Date exitDate) {
        Employee employee=new Employee();
        employee.setEmployeeName("Vikash");
        employee.setAmount(5000);
        employee.setCurrency("INR");
        employee.setExitDate(exitDate);
        employee.setJoiningDate(joinDate);
        return employee;
    }
    
}
