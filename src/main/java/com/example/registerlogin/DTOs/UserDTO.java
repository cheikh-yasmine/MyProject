package com.example.registerlogin.DTOs;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int employeeId;


    private String employeeName;
    private String lastName;

    private String email;

    private String password;
    private String companyName;
    private String number;
}
