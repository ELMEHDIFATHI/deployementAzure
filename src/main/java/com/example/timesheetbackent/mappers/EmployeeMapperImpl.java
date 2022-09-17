package com.example.timesheetbackent.mappers;


import com.example.timesheetbackent.dtos.EmployeeM;
import com.example.timesheetbackent.dtos.User;
import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeDev;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapperImpl {

    public User fromUser(Employee employee) {
        User user = new User();
        BeanUtils.copyProperties(employee, user);
        return user;
    }


    public EmployeeM fromEmployee(EmployeeDev employee) {
        EmployeeM employeeM = new EmployeeM();
        BeanUtils.copyProperties(employee, employeeM);
        return employeeM;
    }


    public EmployeeDev fromEmployeeM(EmployeeM employeeM) {
        EmployeeDev employee = new EmployeeDev();
        BeanUtils.copyProperties(employeeM, employee);
        return employee;
    }
}
