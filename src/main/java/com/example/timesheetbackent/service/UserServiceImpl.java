package com.example.timesheetbackent.service;


import com.example.timesheetbackent.dtos.User;
import com.example.timesheetbackent.mappers.EmployeeMapperImpl;
import com.example.timesheetbackent.model.*;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import com.example.timesheetbackent.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    EmployeeRepositorie employeeRepository;

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    EmployeeMapperImpl dtomappers;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public EmployeeManager saveEmployeeManger(EmployeeManager employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));

        return employeeRepository.save(employee);
    }

    @Override
    public EmployeeDev saveEmployeeDev(EmployeeDev employee) {
        employee.setPassword(bCryptPasswordEncoder.encode(employee.getPassword()));
        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public EmployeeDev saveEmployeeDevM(EmployeeDev employee) {
        return employeeRepository.saveAndFlush(employee);
    }


    @Override
    public Role addRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public User finByUsername(String username) {
        Employee employee = employeeRepository.findByUsername(username);
        User user = dtomappers.fromUser(employee);
        return user;
    }

    @Override
    public Employee addRoleToEmployee(String username, String rolename) {
        Employee employee = employeeRepository.findByUsername(username);
        Role role = roleRepository.findByRole(rolename);

        employee.getRoles().add(role);
        return employee;
    }

    @Override
    public List<User> findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        List<User> employeDTOSList = employeeList.
                stream().
                map(employee -> dtomappers.fromUser(employee)).
                collect(Collectors.toList());

        return employeDTOSList;
    }

    @Override
    public Employee findUserByUsername(String username) {
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findByRole(String role) {
        Role role1 = roleRepository.findByRole(role);
        return employeeRepository.findAll()
                .stream().filter(e -> e.getRoles().contains(role1)).collect(Collectors.toList());
    }

    @Override
    public void deleteProjectById(Long id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        employee.setRoles(null);
        employeeRepository.delete(employee);
    }

}
