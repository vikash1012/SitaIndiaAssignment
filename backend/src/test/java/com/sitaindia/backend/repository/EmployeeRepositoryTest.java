package com.sitaindia.backend.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

import com.sitaindia.backend.controller.dto.CreateEmployeeResponse;
import com.sitaindia.backend.controller.dto.EmployeeResponse;
import com.sitaindia.backend.model.Employee;

@ExtendWith(MockitoExtension.class)
public class EmployeeRepositoryTest {
    EmployeeRepository employeeRepository;

    @Mock
    JPAEmployeeRepository jpaEmployeeRepository;


    @BeforeEach
    void init(){
        employeeRepository=new EmployeeRepository(jpaEmployeeRepository);
    }

    @Test
    void shouldReturnAllEmployee(){
        Date date=Date.valueOf("2001-12-10");
        List<EmployeeResponse> employeeResponses= List.of(new EmployeeResponse("vikash", 10000));
         List<CreateEmployeeResponse> expected=List.of(new CreateEmployeeResponse("INR", employeeResponses));
        when(this.jpaEmployeeRepository.findListOfUniqueCurrencies()).thenReturn(List.of("INR"));
        when(this.jpaEmployeeRepository.findEmployee("INR", date)).thenReturn(employeeResponses);
       
        List<CreateEmployeeResponse> actual=employeeRepository.getAllEmployee(date);

        assertEquals(expected,actual);
        verify(jpaEmployeeRepository,times(1)).findListOfUniqueCurrencies();
         verify(jpaEmployeeRepository,times(1)).findEmployee("INR", date);
    }

    @Test
    void shouldSaveEmployeeRequest(){
        Date exiDate=Date.valueOf("2023-12-10");
        Date joinDate=Date.valueOf("2001-12-10");
        Employee employee = createEmployee(joinDate,exiDate);
        List<Employee> employees=List.of(employee);

        this.employeeRepository.create(employees);

        verify(jpaEmployeeRepository, times(1)).saveAll(employees);
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
