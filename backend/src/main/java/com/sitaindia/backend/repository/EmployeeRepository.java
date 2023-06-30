package com.sitaindia.backend.repository;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.sitaindia.backend.controller.dto.CreateEmployeeResponse;
import com.sitaindia.backend.controller.dto.EmployeeResponse;
import com.sitaindia.backend.model.Employee;

@Repository
public class EmployeeRepository {
    
    JPAEmployeeRepository jpaEmployeeRepository;

    public EmployeeRepository(JPAEmployeeRepository jpaEmployeeRepository) {

        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }
    public List<CreateEmployeeResponse> getAllEmployee(Date date){
          List<String> currencies = this.jpaEmployeeRepository.findListOfUniqueCurrencies();

        return currencies.stream()
                .map(currency -> {
                    List<EmployeeResponse> employees = this.jpaEmployeeRepository.findEmployee(currency,date);
                    return new CreateEmployeeResponse(currency, employees);
                })
                .collect(Collectors.toList());
    }
    public void create(List<Employee> employees) {
        this.jpaEmployeeRepository.saveAll(employees);
    }

    
}
