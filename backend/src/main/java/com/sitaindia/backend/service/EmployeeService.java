package com.sitaindia.backend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sitaindia.backend.controller.dto.CreateEmployeeRequest;
import com.sitaindia.backend.model.Department;
import com.sitaindia.backend.model.Employee;
import com.sitaindia.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    EmployeeRepository employeeRepository;
    private final static List<String> months=List.of("january","february","march","april","may","june","july","august","september","october","november","december");

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public void create(List<CreateEmployeeRequest> employeeRequests) {
        List<Employee> employees=new ArrayList<>();
        System.out.println(employeeRequests.get(0).getJoiningDate());
        employees=employeeRequests.stream().map(emp->{
            Employee employee=new Employee();
            employee.setEmployeeName(emp.getEmpName());
            employee.setAmount(emp.getAmount());
            employee.setCurrency(emp.getCurrency());
            employee.setJoiningDate(parseDate(emp.getJoiningDate()));
            employee.setExitDate(parseDate(emp.getExitDate()));
            Department department=new Department();
            department.setDepartment(emp.getDepartment());
            employee.setDepartment(department);
            return employee;
        }
        ).collect(Collectors.toList());
        this.employeeRepository.create(employees);
      }

    private Date parseDate(String date) {
        String[] splitDate=date.split("-");
        int getMonth=getMonthNumber(splitDate[0]);
        // System.out.println(getMonth);
        return Date.valueOf(splitDate[2]+"-"+getMonth+"-"+splitDate[1]);
    }

    private int getMonthNumber(String month) {
        for(int i=0;i<months.size();i++){
            if(months.get(i).contains(month)){
                return i+1;
            }
        }
        return 0;
    }

    
}
