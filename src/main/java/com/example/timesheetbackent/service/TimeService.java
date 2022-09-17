package com.example.timesheetbackent.service;


import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.Time;
import com.example.timesheetbackent.repository.TimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TimeService {

    @Autowired
    TimeRepository timeRepository;
    @Autowired
    EmployeeService employeeService;

    public Time addTaskToEmployeeDevById(Long id, Time time){
        EmployeeDev employeeDev = employeeService.findEmployeeDevById(id);
        if(employeeDev == null) return null;
        time.setEmployeeDev(employeeDev);
        return timeRepository.save(time);
    }

    public List<Time> findAllTimeEmployeeId(Long id){
        EmployeeDev employeeDev = employeeService.findEmployeeDevById(id);
        if(employeeDev == null) return null;
        return timeRepository.findByEmployeeDevOrderByDateDesc(employeeDev);

    }
}
