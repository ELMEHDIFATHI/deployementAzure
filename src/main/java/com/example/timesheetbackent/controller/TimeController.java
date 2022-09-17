package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.model.Time;
import com.example.timesheetbackent.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/time")
@CrossOrigin("*")
public class TimeController {

    @Autowired
    TimeService timeService;


    @CrossOrigin("*")
    @GetMapping("/findAllTimeEmployeeById/{id}")
    public List<Time> findAllTimeEmployeeById(@PathVariable("id") Long id){
        return timeService.findAllTimeEmployeeId(id);
    }


    @CrossOrigin("*")
    @PostMapping("/addTimeToEmplyeeById/{id}")
    public Time addTimeToEmployeeById(@PathVariable("id") Long id,@RequestBody Time time){
        return timeService.addTaskToEmployeeDevById(id,time);
    }
}
