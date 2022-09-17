package com.example.timesheetbackent.service;


import com.example.timesheetbackent.dtos.EmployeeM;
import com.example.timesheetbackent.mappers.EmployeeMapperImpl;
import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    EmployeeRepositorie employeeRepositorie;
    @Autowired
    EmployeeMapperImpl employeeMapper;

    public Employee finEmployeeMangerById(Long id) {
        Employee employee = employeeRepositorie.findById(id).orElse(null);
        if (!(employee instanceof EmployeeManager) || (employee == null)) {
            return null;
        }
        return employee;
    }

    public EmployeeDev findEmployeeDevById(Long id) {
        Employee employee = employeeRepositorie.findById(id).orElse(null);
        if (!(employee instanceof EmployeeDev) || (employee == null)) {
            return null;
        }
        return (EmployeeDev) employee;
    }


    public List<EmployeeM> ListEmployeeDevM() {
        List<Employee> employeeList = employeeRepositorie.findAll().stream().filter(t -> t instanceof EmployeeDev).collect(Collectors.toList());
        return employeeList.stream().map(t -> employeeMapper.fromEmployee((EmployeeDev) t)).collect(Collectors.toList());
    }


}
