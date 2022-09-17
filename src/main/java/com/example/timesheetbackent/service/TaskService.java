package com.example.timesheetbackent.service;


import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.Project;
import com.example.timesheetbackent.model.Task;
import com.example.timesheetbackent.repository.ProjectRepository;
import com.example.timesheetbackent.repository.TaskRepositorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    TaskRepositorie taskRepositorie;

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeService employeeService;

    public Task addTaskToProjectId(Long idP, Long idE, Task task) {
        Project project = projectRepository.findById(idP).orElse(null);
        EmployeeDev employeeDev = employeeService.findEmployeeDevById(idE);
        if (project == null || employeeDev == null) return null;
        task.setProjectTask(project);
        task.setEmployeeTask(employeeDev);
        project.setStatus();
        return taskRepositorie.save(task);
    }

    public List<Task> findAllTaskByEmployeeDev(Long id) {
        EmployeeDev employeeDev = employeeService.findEmployeeDevById(id);
        if (employeeDev == null) return null;
        return taskRepositorie.findByEmployeeTask(employeeDev);

    }

    public List<Task> findAllTaskByProjectId(Long id) {
        List<Task> taskList = new ArrayList<>();
        Project project = projectRepository.findById(id).orElse(null);

        taskList = project.getTaskList();
        return taskList;
    }

    public void deleteTaskById(Long id) {
        Task task = taskRepositorie.findById(id).orElse(null);
        taskRepositorie.delete(task);
    }

    public Task editTask(Long id, Task task) {
        Task task1 = taskRepositorie.findById(id).orElse(null);
        if (task1 == null) {
            return null;
        }
        System.out.println(""+task);
        task1.setEmployeeTask(task.getEmployeeTask());
        task1.setNom(task.getNom());
        task1.setDescription(task.getDescription());
        task1.setDuration(task.getDuration());
        task1.setStatus(task.getStatus());
        task1.setVerified(task.isVerified());
        task1.setProjectTask(task.getProjectTask());
        task1.getProjectTask().setStatus();
        System.err.println(""+task1.getProjectTask());

        return taskRepositorie.save(task);
    }

    public Task saveTask(Task task) {
        Task task1 = taskRepositorie.findById(task.getId_Task()).orElse(null);
        task1.setStatus(task.getStatus());
        task1.setDuration(task.getDuration());
        task1.getProjectTask().setDuration();
        return taskRepositorie.save(task1);


    }
}
