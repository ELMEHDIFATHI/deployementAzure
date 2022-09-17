package com.example.timesheetbackent.repository;

import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventRepository extends JpaRepository<Event,Long> {

    List<Event> findAllByEmployeeManagerEvent(EmployeeManager employeeManager);
 }
