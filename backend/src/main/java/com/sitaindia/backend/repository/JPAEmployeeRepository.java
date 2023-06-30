package com.sitaindia.backend.repository;


import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sitaindia.backend.controller.dto.EmployeeResponse;
import com.sitaindia.backend.model.Employee;

public interface JPAEmployeeRepository extends JpaRepository<Employee, Integer> {

    // @Query("select u from Employee u WHERE u.joiningDate<=:d and u.exitDate>=:d group by u.currency order by u.employeeName asc")
    //  @Query("SELECT new com.sitaindia.backend.controller.dto.CreateEmployeeResponse(e.currency, " +
    //         "JSON_ARRAYAGG(new com.sitaindia.backend.controller.dto.EmployeeResponse(e.employeeName, e.amount))) " +
    //         "FROM Employee e WHERE e.joiningDate<=:d and e.exitDate>=:d" +
    //         "GROUP BY e.currency order by e.employeeName asc")
    
    @Query("SELECT new com.sitaindia.backend.controller.dto.EmployeeResponse(e.employeeName, e.amount) " +
            "FROM Employee e WHERE e.currency = :currency and e.joiningDate<=:date and e.exitDate>=:date order by e.employeeName asc")
    List<EmployeeResponse> findEmployee(@Param("currency") String currency,@Param("date") Date date);

    @Query("SELECT DISTINCT e.currency FROM Employee e")
    List<String> findListOfUniqueCurrencies();


}
