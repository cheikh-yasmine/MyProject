package com.example.registerlogin.serviceImpl;

import com.example.registerlogin.DTOs.UserDTO;
import com.example.registerlogin.DTOs.LoginDTO;
import com.example.registerlogin.entity.User;
import com.example.registerlogin.exception.UserNotFoundException;
import com.example.registerlogin.repository.UserRepository;
import com.example.registerlogin.response.LoginResponse;
import com.example.registerlogin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(UserDTO employeeDTO) {

        User employee = new User(
                employeeDTO.getEmployeeId(),
                employeeDTO.getEmployeeName(),
                employeeDTO.getLastName(),
                employeeDTO.getEmail(),
                this.passwordEncoder.encode(employeeDTO.getPassword()),
                employeeDTO.getCompanyName(),
                employeeDTO.getNumber()
        );
        employeeRepository.save(employee);

        return employee.getEmployeeName();
    }
    @Override
    public String deleteEmployeeById(Integer id) {
        Optional<User> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return "Employee deleted successfully";
        } else {
            return "Employee with ID " + id + " not found";
        }
    }
    @Override
    public User getById(Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(() -> new UserNotFoundException(employeeId));
    }
    @Override
    public User updateEmployee(Integer employeeId, User updatedEmployeeDetails) {
        // Retrieve the existing employee
        User existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new UserNotFoundException(employeeId));

        // Update the properties of the existing employee
        existingEmployee.setEmployeeName(updatedEmployeeDetails.getEmployeeName());
        existingEmployee.setLastName(updatedEmployeeDetails.getLastName());
        existingEmployee.setEmail(updatedEmployeeDetails.getEmail());
        existingEmployee.setPassword(updatedEmployeeDetails.getPassword());
        existingEmployee.setCompanyName(updatedEmployeeDetails.getCompanyName());

        // Save the updated employee and return it
        return employeeRepository.save(existingEmployee);
    }


    @Override
    public LoginResponse loginEmployee(LoginDTO loginDTO) {
        String msg = "";
        User employee1 = employeeRepository.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<User> employee = employeeRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                if (employee.isPresent()) {
                    return new LoginResponse("Login Success ", true);
                } else {
                    return new LoginResponse("Login Failed ", false);

                }
            } else {
                return new LoginResponse("Password Not Match ", false);
            }
        } else {
            return new LoginResponse("Email Not Exists ", false);
        }

    }

}

