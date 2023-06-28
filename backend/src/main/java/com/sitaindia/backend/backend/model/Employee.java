package com.sitaindia.backend.backend.model;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@Setter
@Entity
@Table(name= "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    private int id;

    @Column(name="emp_name")
    private String employeeName;

    private int amount;
    private String currency;

    @Column(name="joining_date")
    private Date joiningDate;

    @Column(name="exit_date")
    private Date exitDate;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="emp_id")
    private Department department;
    
}
