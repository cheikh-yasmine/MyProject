package com.example.registerlogin.serviceImpl;

import com.example.registerlogin.dto.EmployeeDTO;
import com.example.registerlogin.dto.LoginDTO;
import com.example.registerlogin.entity.Employee;
import com.example.registerlogin.exception.EmployeeNotFoundException;
import com.example.registerlogin.repository.EmployeeRepository;
import com.example.registerlogin.response.LoginResponse;
import com.example.registerlogin.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addEmployee(EmployeeDTO employeeDTO) {

        Employee employee = new Employee(
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
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            employeeRepository.delete(employee.get());
            return "Employee deleted successfully";
        } else {
            return "Employee with ID " + id + " not found";
        }
    }
    @Override
    public Employee updateEmployee(Integer employeeId, Employee updatedEmployeeDetails) {
        // Retrieve the existing employee
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));

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
        Employee employee1 = employeeRepository.findByEmail(loginDTO.getEmail());
        if (employee1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = employee1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            if (isPwdRight) {
                Optional<Employee> employee = employeeRepository.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
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

