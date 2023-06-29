package com.sitaindia.backend.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sitaindia.backend.model.Employee;

@Repository
public class EmployeeRepository {
    
    JPAEmployeeRepository jpaEmployeeRepository;

    @Autowired
    public EmployeeRepository(JPAEmployeeRepository jpaEmployeeRepository) {
        
        this.jpaEmployeeRepository = jpaEmployeeRepository;
    }
    public void create(List<Employee> employees) {
        this.jpaEmployeeRepository.saveAll(employees);
    }

    
}
