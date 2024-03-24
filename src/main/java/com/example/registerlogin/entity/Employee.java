package com.example.registerlogin.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Employee {

    @Id
    @Column(name = "employee_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;


    @Column(name = "employee_name", length = 255)
    private String employeeName;
    @Column(name= "last_name" , length = 255)
    private String lastName;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;
    @Column(name= "company_name", length = 255 )
    private String companyName;
    @Column(name="number" , length= 255 )
    private String number;
}
