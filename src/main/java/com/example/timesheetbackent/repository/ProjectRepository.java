package com.example.timesheetbackent.repository;

import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.model.EmployeeManager;
import com.example.timesheetbackent.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Long> {

    List<Project> findByManagerProject(Employee manager);

    @Query(value = "SELECT COUNT(*) FROM project WHERE project.status = 'Done' " , nativeQuery = true)
    Integer DoneProject();

    @Query(value = "SELECT COUNT(*) FROM project WHERE project.status = 'InProgress' " , nativeQuery = true)
    Integer InProgressProject();


}
