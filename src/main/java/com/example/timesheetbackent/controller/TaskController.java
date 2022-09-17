package com.example.timesheetbackent.controller;


import com.example.timesheetbackent.model.Task;
import com.example.timesheetbackent.model.chart.TaskChart;
import com.example.timesheetbackent.repository.TaskRepositorie;
import com.example.timesheetbackent.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/task")
@CrossOrigin("*")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Autowired
    TaskRepositorie taskRepositorie;

    @CrossOrigin("*")
    @PostMapping("/addTaskToProject/{idP}/{idE}")
    public Task addTaskToProjectById(@PathVariable("idP") Long idP,@PathVariable("idE") Long idE,@RequestBody Task task){
        return taskService.addTaskToProjectId(idP,idE,task);
    }

    @CrossOrigin("*")
    @GetMapping("/fidAllTaskByEmployee/{id}")
    public List<Task> fidAllTaskByEmployee(@PathVariable("id") Long id){
        return taskService.findAllTaskByEmployeeDev(id);
    }

    @CrossOrigin("*")
    @GetMapping("/findAllTaskByProjectId/{id}")
    public List<Task> findAllTaskByProjectId(@PathVariable("id") Long id){
        return taskService.findAllTaskByProjectId(id);
    }


    @CrossOrigin("*")
    @RequestMapping(value = "/deleteTaskById/{id}",method = RequestMethod.DELETE)
    public void deleteTaskById(@PathVariable("id") Long id){

        taskService.deleteTaskById(id);
    }


    @CrossOrigin("*")
    @PostMapping ("/editTask/{id}")
    public Task editTask(@PathVariable("id") Long id ,@RequestBody Task task){
        return taskService.editTask(id,task);
    }


    @CrossOrigin("*")
    @GetMapping("/ChartTask/{id}")
    public TaskChart ChartTask(@PathVariable("id") Long id){

        TaskChart taskChart = new TaskChart();
        taskChart.setTaskDone(taskRepositorie.taskDone(id));
        taskChart.setTaskInprogress(taskRepositorie.taskInProgress(id));
        taskChart.setTaskTodo(taskRepositorie.taskTodo(id));
        return  taskChart;
    }

    @CrossOrigin("*")
    @PutMapping("/editTaskForEmplyee/")
    public Task editTaskForEmplyee(@RequestBody Task task){
       return  taskService.saveTask(task);
    }






}
