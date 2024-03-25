package com.example.registerlogin.serviceImpl;

import com.example.registerlogin.dto.EmployeeDTO;
import com.example.registerlogin.dto.LoginDTO;
import com.example.registerlogin.entity.Employee;
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
    public void deleteUser(Long employeeId) {

        EmployeeRepository.deleteById(employeeId);   }
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

