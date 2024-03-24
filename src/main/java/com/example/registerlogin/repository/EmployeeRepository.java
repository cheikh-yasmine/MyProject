package com.example.registerlogin.repository;

import com.example.registerlogin.entity.Employee;
import lombok.Getter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@EnableJpaRepositories
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    Optional <Employee> findOneByEmailAndPassword(String email,String password);
    Employee findByEmail(String email);
}
