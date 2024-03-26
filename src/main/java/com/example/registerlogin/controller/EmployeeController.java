package com.example.registerlogin.controller;

import com.example.registerlogin.dto.EmployeeDTO;
import com.example.registerlogin.dto.LoginDTO;
import com.example.registerlogin.response.LoginResponse;
import com.example.registerlogin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("api/v1/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/save")
    public String saveEmployee(@RequestBody EmployeeDTO employeeDto){
        String id =employeeService.addEmployee(employeeDto);
        return id;

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Integer id) {
        String message = employeeService.deleteEmployeeById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse= employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
