
package com.example.timesheetbackent.service;



import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.model.chart.Dashboard;
import com.example.timesheetbackent.model.chart.TaskChart;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import com.example.timesheetbackent.repository.ProjectRepository;
import com.example.timesheetbackent.repository.TaskRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class DashboardService {


    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepositorie employeeRepositorie;

    @Autowired
    TaskRepositorie taskRepositorie;


    public Dashboard getGlobal() {
        Dashboard dashboard = new Dashboard();
        Map<String, Float> map = new HashMap<>();
        List<String> pn = new ArrayList<>();
        List<Integer> t = new ArrayList<>();
        List<Integer> i = new ArrayList<>();
        List<Integer> d = new ArrayList<>();
        projectRepository.findAll().forEach(project -> {
            map.put(project.getName(), project.getDuration());
            pn.add(project.getName());
            t.add(taskRepositorie.taskDone(project.getId_Project()));
            i.add(taskRepositorie.taskInProgress(project.getId_Project()));
            d.add(taskRepositorie.taskTodo(project.getId_Project()));
        });

        dashboard.setTaskDoneNoVerified(taskRepositorie.taskDoneNoVerified());
        dashboard.setProjectDuration(map);
        dashboard.setProjectName(pn);
        dashboard.setProjectTaskTodo(t);
        dashboard.setProjectTaskInProgress(i);
        dashboard.setProjectTaskDone(d);
        dashboard.setTotalProject(projectRepository.findAll().size());
        dashboard.setTotalEmployee((int) employeeRepositorie.findAll().stream().filter(EmployeeDev.class::isInstance).count());
        dashboard.setDoneProject(projectRepository.DoneProject());
        dashboard.setInProgressProject(projectRepository.InProgressProject());
        dashboard.setTotalManger((int) employeeRepositorie.findAll().stream().filter(EmployeeManager.class::isInstance).count());

        return dashboard;
    }
}
