package com.example.registerlogin.exception;


    public class UserNotFoundException extends RuntimeException {

        public UserNotFoundException(Integer employeeId) {
            super("Employee with ID " + employeeId + " not found");
        }
    }


