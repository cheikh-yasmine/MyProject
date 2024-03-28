package com.example.registerlogin.exception;


    public class EmployeeNotFoundException extends RuntimeException {

        public EmployeeNotFoundException(Integer employeeId) {
            super("Employee with ID " + employeeId + " not found");
        }
    }


