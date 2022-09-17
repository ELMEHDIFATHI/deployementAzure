package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.dtos.EmployeeM;
import com.example.timesheetbackent.mappers.EmployeeMapperImpl;
import com.example.timesheetbackent.model.*;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import com.example.timesheetbackent.repository.EventRepository;
import com.example.timesheetbackent.service.EmployeeService;
import com.example.timesheetbackent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {


    @Autowired
    EmployeeRepositorie employeeRepositorie;

    @Autowired
    EventRepository eventRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapperImpl em;


    @Autowired
    UserService userService;

    @GetMapping("/findAll")
    public List<Employee> findAll() {
        return employeeRepositorie.findAll();
    }

    @GetMapping("/findAll/Dev")
    public List<Employee> findAllDev() {
        return employeeRepositorie.findAll().stream().filter(EmployeeDev.class::isInstance).toList();
    }

    @GetMapping("/findAllM/Dev")
    public List<EmployeeM> findAllDevM() {
        return employeeService.ListEmployeeDevM();
    }


    @GetMapping("/findAll/Manager")
    public List<Employee> findAllManager() {
        return employeeRepositorie.findAll().stream().filter(t -> t instanceof EmployeeManager).collect(Collectors.toList());
    }

    @GetMapping("/findById/Manager/{id}")
    public Employee findManagerById(@PathVariable("id") Long id) {
        Employee employee =employeeRepositorie.findById(id).orElse(null);
        if (!(employee instanceof EmployeeManager) || employee == null){
            return null;
        }
        return employee;
    }

    @GetMapping("/findById/Dev/{id}")
    public Employee findDevById(@PathVariable("id") Long id) {
        Employee employee =employeeRepositorie.findById(id).orElse(null);
        if (!(employee instanceof EmployeeDev) || employee == null){
            return null;
        }
        return employee;
    }



    @GetMapping("/findAllEvent/{id}")
    public List<Event> findAllEventByDev(@PathVariable("id") Long id){
        Employee  employee = employeeRepositorie.findById(id).orElse(null);
        if (employee instanceof EmployeeDev){
            return ((EmployeeDev) employee).getEventList();
        }
        if (employee instanceof EmployeeManager){

            return eventRepository.findAllByEmployeeManagerEvent((EmployeeManager) employee);
        }
        return null;

    }



    @PostMapping("/saveEmployeeDev")
    public EmployeeDev saveEmployeeDev(@RequestBody EmployeeDev employeeDev){
         userService.saveEmployeeDev(employeeDev);
         userService.addRoleToEmployee(employeeDev.getUsername(),"USER");
         return (EmployeeDev) employeeRepositorie.findByUsername(employeeDev.getUsername());
    }


    @PostMapping("/saveEmployeeDevM")
    public EmployeeDev saveEmployeeDevM(@RequestBody EmployeeDev employeeDev){
        return userService.saveEmployeeDevM(employeeDev);
    }

    @PostMapping("/saveEmployeeManger")
    public EmployeeManager saveEmployeeManger(@RequestBody EmployeeManager employeeManager){
        userService.saveEmployeeManger(employeeManager);
        userService.addRoleToEmployee(employeeManager.getUsername(),"USER");
        userService.addRoleToEmployee(employeeManager.getUsername(),"ADMIN");
        return (EmployeeManager) employeeRepositorie.findByUsername(employeeManager.getUsername());
    }


    @PostMapping("/saveEmployee")
    public Employee saveEmployee(@RequestBody Employee employee){
       return employeeRepositorie.save(employee);
    }


    @CrossOrigin("*")
    @RequestMapping(value = "/deleteEmployeeById/{id}",method = RequestMethod.DELETE)
    public void deleteEmployeeById(@PathVariable("id") Long id){
        System.out.println(" id " + id);
        userService.deleteProjectById(id);
    }


    @CrossOrigin("*")
    @PutMapping("/saveEmployeeDevM")
    public EmployeeDev editEmplyeeDev(@RequestBody EmployeeDev employeeDev){
        Employee employee = employeeRepositorie.findById(employeeDev.getId()).orElse(null);
        employee.setEmail(employeeDev.getEmail());
        employee.setFirstName(employeeDev.getFirstName());
        employee.setLastName(employeeDev.getLastName());
        employee.setPhoto(employeeDev.getPhoto());
        return (EmployeeDev) employeeRepositorie.save(employee);
    }


    @CrossOrigin("*")
    @PutMapping("/saveEmployeeMangerM")
    public EmployeeManager editEmployyeManger(@RequestBody EmployeeManager employeeManager){
        Employee employee = employeeRepositorie.findById(employeeManager.getId()).orElse(null);
        employee.setEmail(employeeManager.getEmail());
        employee.setFirstName(employeeManager.getFirstName());
        employee.setLastName(employeeManager.getLastName());
        employee.setPhoto(employeeManager.getPhoto());
        return (EmployeeManager) employeeRepositorie.save(employee);
    }



}
