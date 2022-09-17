package com.example.timesheetbackent.repository;

import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.Time;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeRepository extends JpaRepository<Time, Long> {

    List<Time> findByEmployeeDevOrderByDateDesc(EmployeeDev employeeDev);
}
