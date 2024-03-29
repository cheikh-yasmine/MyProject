package com.example.registerlogin.service;

import com.example.registerlogin.DTOs.EmployeeDTO;
import com.example.registerlogin.DTOs.LoginDTO;
import com.example.registerlogin.entity.Employee;
import com.example.registerlogin.response.LoginResponse;

public interface EmployeeService {
    String addEmployee(EmployeeDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);

    String deleteEmployeeById(Integer id);

    Employee getById(Integer id);
    Employee updateEmployee(Integer id, Employee updatedEmployeeDetails);
}
