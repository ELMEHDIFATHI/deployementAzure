package com.example.timesheetbackent.model.chart;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskChart {
    private  int taskTodo;
    private  int taskInprogress;
    private  int taskDone;
}
