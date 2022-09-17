package com.example.timesheetbackent.service;


import com.example.timesheetbackent.model.*;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import com.example.timesheetbackent.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
public class EventService {


    @Autowired
    EventRepository eventRepository;
    @Autowired
    EmployeeRepositorie  employeeRepositorie;

    public Event addEvent(Long id,Long[] ids ,Event event){

        if (event.getId() == null){
        Event event1 = new Event();
        Employee employee = employeeRepositorie.findById(id).orElse(null);
        if(!(employee instanceof EmployeeManager) || (employee == null)){
            return null;
        }
        event1.setEmployeeManagerEvent((EmployeeManager) employee);
        event1.setTitle(event.getTitle());
        event1.setStart(event.getStart());
        event1.setEnd(event.getEnd());


        Arrays.stream(ids).forEach(i->{
            Employee employee1 = employeeRepositorie.findById(i).orElse(null);
            if(employee1 instanceof EmployeeDev){
                ((EmployeeDev) employee1).getEventList().add(event1);
            }
        });

        return eventRepository.save(event1);}
        else{
            Event event1 = eventRepository.findById(event.getId()).orElse(null);
            Employee employee = employeeRepositorie.findById(id).orElse(null);
            if(!(employee instanceof EmployeeManager) || (employee == null)){
                return null;
            }
            event.setId(event.getId());
            event1.setEmployeeManagerEvent((EmployeeManager) employee);
            event1.setTitle(event.getTitle());
            event1.setStart(event.getStart());
            event1.setEnd(event.getEnd());


            Arrays.stream(ids).forEach(i->{
                Employee employee1 = employeeRepositorie.findById(i).orElse(null);
                if(employee1 instanceof EmployeeDev){
                    ((EmployeeDev) employee1).getEventList().add(event1);
                }
            });

            return eventRepository.save(event1);}
        }



    public List<Event> getAllEvent(Long id){
        Employee employee = employeeRepositorie.findById(id).orElse(null);
        if(employee instanceof EmployeeManager){
            return ((EmployeeManager) employee).getEventList();
        }
        else if(employee instanceof EmployeeDev){
            return ((EmployeeDev) employee).getEventList();
        }
        else return null;
    }


    public void deleteEventById(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        event.getEmployeeDevList().forEach(employeeDev -> employeeDev.getEventList().remove(event));
        event.setEmployeeDevList(null);
        event.setEmployeeManagerEvent(null);
        System.err.println(""+event);
        eventRepository.delete(event);
    }
}
