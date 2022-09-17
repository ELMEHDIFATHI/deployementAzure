package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.model.Project;
import com.example.timesheetbackent.repository.ProjectRepository;
import com.example.timesheetbackent.service.EmployeeService;
import com.example.timesheetbackent.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/project")
@CrossOrigin("*")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/findAllProject")
    public List<Project> findAll(){
        return projectRepository.findAll();
    }
    @GetMapping("/findProjectById/{id}")
    public Project findProjectById(@PathVariable("id") Long id){
        return  projectService.findProjectById(id);
    }


    @GetMapping("/findAllProject/byEmployeeManager/{id}")
    public List<Project> findAllByEmployeeId(@PathVariable("id") Long id){
        return  projectService.findAllProjectByEmplId(id);
    }



    @PostMapping(value = "/addProject/{id}")
    public Project addProjectToManagerId(@PathVariable("id") Long id ,@RequestBody Project project){
        Employee employee = employeeService.finEmployeeMangerById(id);
        project.setManagerProject((EmployeeManager) employee);
        return projectService.addProject(project);
    }



    @PostMapping("/save")
    public Project sav(@RequestBody Project p){
        return projectService.save(p);
    }

    @CrossOrigin("*")
    @RequestMapping(value = "/deleteProjectById/{id}",method = RequestMethod.DELETE)
    public void deleteProjectById(@PathVariable("id") Long id){
        System.out.println(" id " + id);
        projectService.deleteProjectById(id);
    }


}
