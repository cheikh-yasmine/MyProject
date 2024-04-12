package com.example.registerlogin.service;

import com.example.registerlogin.DTOs.UserDTO;
import com.example.registerlogin.DTOs.LoginDTO;
import com.example.registerlogin.entity.User;
import com.example.registerlogin.response.LoginResponse;

public interface UserService {
    String addEmployee(UserDTO employeeDTO);
    LoginResponse loginEmployee(LoginDTO loginDTO);

    String deleteEmployeeById(Integer id);

    User getById(Integer id);
    User updateEmployee(Integer id, User updatedEmployeeDetails);
}
