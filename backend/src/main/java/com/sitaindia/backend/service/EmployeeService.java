package com.sitaindia.backend.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sitaindia.backend.controller.dto.CreateEmployeeRequest;
import com.sitaindia.backend.controller.dto.CreateEmployeeResponse;
import com.sitaindia.backend.controller.dto.EmployeeRequest;
import com.sitaindia.backend.controller.dto.GetAllEmployeeResponse;
import com.sitaindia.backend.model.Department;
import com.sitaindia.backend.model.Employee;
import com.sitaindia.backend.repository.EmployeeRepository;

@Service
public class EmployeeService {
    
    EmployeeRepository employeeRepository;

    private final static List<String> months=List.of("january","february","march","april","may","june","july","august","september","october","november","december");
 
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    public void create(CreateEmployeeRequest employees) {
        List<EmployeeRequest> employeeRequestList=employees.getEmployees();
        List<Employee> employeesData=new ArrayList<>();
        employeesData = parsingRequestToDataModel(employeeRequestList);
        System.out.println(employeesData.get(0).getJoiningDate());
        this.employeeRepository.create(employeesData);
      }

    public GetAllEmployeeResponse getEmployee(String date) {
        Date parsedDate=parseDate(date);
        List<CreateEmployeeResponse> employees=this.employeeRepository.getAllEmployee(parsedDate);
        return new GetAllEmployeeResponse(employees);

    }

    private List<Employee> parsingRequestToDataModel(List<EmployeeRequest> employeeRequests) {
        List<Employee> employees;
        employees=employeeRequests.stream().map(emp->{
            Employee employee = parsingRequestToEmployeeModel(emp);
            Department department = parsingRequestToDepartmentModel(emp);
            employee.setDepartment(department);
            return employee;
        }
        ).collect(Collectors.toList());
        return employees;
    }

    private Department parsingRequestToDepartmentModel(EmployeeRequest emp) {
        Department department=new Department();
        department.setDepartment(emp.getDepartment());
        return department;
    }

    private Employee parsingRequestToEmployeeModel(EmployeeRequest emp) {
        Employee employee=new Employee();
        employee.setEmployeeName(emp.getEmpName());
        employee.setAmount(emp.getAmount());
        employee.setCurrency(emp.getCurrency());
        employee.setJoiningDate(parseDate(emp.getJoiningDate()));
        employee.setExitDate(parseDate(emp.getExitDate()));
        return employee;
    }

    private Date parseDate(String date) {
        String[] splitDate=date.split("-");
        int getMonth=getMonthNumber(splitDate[0]);
        return Date.valueOf(splitDate[2]+"-"+getMonth+"-"+splitDate[1]);
    }

    private int getMonthNumber(String month) {
        for(int i=0;i<months.size();i++){
            if(months.get(i).contains(month.toLowerCase())){
                return i+1;
            }
        }
        return 0;
    }

    
}
