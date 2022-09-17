package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.dtos.User;
import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/login/{username}")
    public  User getEmployeeDTOByUsernamePassword(@PathVariable("username") String username){
        return userService.finByUsername(username);
    }

    @GetMapping("/find/{username}")
    public Employee findEmployeeByUsername(@PathVariable("username")String username){
        return  userService.findUserByUsername(username);
    }

    @GetMapping("/find/listEmployeeByRole/{role}")
    public List<Employee> findByRole(@PathVariable("role") String role){
        return userService.findByRole(role);
    }
}
