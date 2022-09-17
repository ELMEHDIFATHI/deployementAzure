package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.model.chart.Dashboard;
import com.example.timesheetbackent.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private  DashboardService dashboardService;


    @GetMapping("/global")
    public Dashboard globalForDashboard(){
        return dashboardService.getGlobal();
    }
}
