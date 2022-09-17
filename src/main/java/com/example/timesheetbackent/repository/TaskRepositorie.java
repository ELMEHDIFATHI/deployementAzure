package com.example.timesheetbackent.repository;

import com.example.timesheetbackent.model.EmployeeDev;
import com.example.timesheetbackent.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepositorie extends JpaRepository<Task,Long> {
    List<Task> findByEmployeeTask(EmployeeDev employeeTask);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_task_id_project = :id AND task.status like 'ToDo' " , nativeQuery = true)
    Integer taskTodo(@Param("id") Long id);

    @Query(value = " SELECT COUNT(*) FROM task WHERE project_task_id_project = :id AND task.status like 'InProgress'" , nativeQuery = true)
    Integer taskInProgress(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM task WHERE project_task_id_project = :id AND task.status like 'Done' " , nativeQuery = true)
    Integer taskDone(@Param("id") Long id);

    @Query(value = "SELECT COUNT(*) FROM task WHERE task.status like 'Done' AND task.verified =0" , nativeQuery = true)
    Integer taskDoneNoVerified();


}
