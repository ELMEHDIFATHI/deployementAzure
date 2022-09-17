package com.example.timesheetbackent.service;


import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.model.Project;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import com.example.timesheetbackent.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepositorie employeeRepositorie;


    public List<Project> findAllProjectByEmplId(Long id){

        Employee employee = employeeRepositorie.findById(id).orElse(null);
        if (!(employee instanceof EmployeeManager) || employee == null){
            return null;
        }

        List<Project> projectList = projectRepository.findByManagerProject(employee);
        projectList.forEach(project -> project.setStatus());
        return projectList;
    }



    public Project addProject(Project project) {
        project.setStatus();
        return projectRepository.save(project);
    }

    public Project save(Project p) {
        return projectRepository.save(p);
    }

    public Project findProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public void deleteProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        System.out.println(""+project);
        projectRepository.delete(project);

    }
}
