package com.sitaindia.backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.sitaindia.backend.model.Employee;

public interface JPAEmployeeRepository extends JpaRepository<Employee, Integer> {


}
