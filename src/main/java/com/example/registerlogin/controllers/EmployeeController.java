package com.example.registerlogin.controllers;

import com.example.registerlogin.DTOs.EmployeeDTO;
import com.example.registerlogin.DTOs.LoginDTO;
import com.example.registerlogin.entity.Employee;
import com.example.registerlogin.exception.EmployeeNotFoundException;
import com.example.registerlogin.response.LoginResponse;
import com.example.registerlogin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping(value = "/getUserById/{id}")
    public ResponseEntity<Employee> getUserById(@PathVariable Integer id) {
        try {
            Employee user = employeeService.getById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EmployeeNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/updateEmployee/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id, @RequestBody Employee updatedEmployeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, updatedEmployeeDetails);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }
    @PostMapping(path = "/login")
    public ResponseEntity<?> loginEmployee(@RequestBody LoginDTO loginDTO){
        LoginResponse loginResponse= employeeService.loginEmployee(loginDTO);
        return ResponseEntity.ok(loginResponse);
    }

}
