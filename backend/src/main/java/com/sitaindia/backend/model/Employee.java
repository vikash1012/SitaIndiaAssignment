package com.sitaindia.backend.model;

import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
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
    @JoinColumn(name="dep_id_fk")
    private Department department;

}
