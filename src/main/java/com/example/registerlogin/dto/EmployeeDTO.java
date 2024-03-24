package com.example.registerlogin.dto;

import jakarta.persistence.Column;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private int employeeId;


    private String employeeName;
    private String lastName;

    private String email;

    private String password;
    private String companyName;
    private String number;
}
