package com.example.timesheetbackent.repository;

import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeDev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepositorie extends JpaRepository<Employee,Long> {

    List<Employee> findAll();
    Employee findByUsername(String username);


}
