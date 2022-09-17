package com.example.timesheetbackent.service;

import com.example.timesheetbackent.dtos.User;
import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.model.Role;

import java.util.List;

public interface UserService {

    EmployeeDev saveEmployeeDev(EmployeeDev employee);
    EmployeeDev saveEmployeeDevM(EmployeeDev employee);
    EmployeeManager saveEmployeeManger(EmployeeManager employee);

    Role addRole(Role role);
    User finByUsername (String username);
    Employee addRoleToEmployee(String username , String rolename);

    List<User> findAll();

    Employee findUserByUsername(String username);

    List<Employee> findByRole(String role);

    void deleteProjectById(Long id);
}
