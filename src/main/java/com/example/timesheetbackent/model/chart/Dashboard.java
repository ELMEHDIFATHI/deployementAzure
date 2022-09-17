package com.example.timesheetbackent.model.chart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dashboard {


    public int totalEmployee;
    public int totalManger;
    public int totalProject;
    public int doneProject;
    public int inProgressProject;
    public Map<String,Float> projectDuration;
    public List<String> projectName;
    public List<Integer> projectTaskTodo;
    public List<Integer> projectTaskInProgress;
    public List<Integer> projectTaskDone;
    public int taskDoneNoVerified;





}
