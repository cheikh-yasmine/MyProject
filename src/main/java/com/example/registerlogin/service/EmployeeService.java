package com.example.registerlogin.service;

import com.example.registerlogin.dto.EmployeeDTO;
import com.example.registerlogin.dto.LoginDTO;
import com.example.registerlogin.entity.Employee;
import com.example.registerlogin.response.LoginResponse;
import org.springframework.http.ResponseEntity;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);


}
