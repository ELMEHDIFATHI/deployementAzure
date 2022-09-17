package com.example.timesheetbackent.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Task;
    private String nom;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String description;
    private int duration;
    private boolean verified;


    @ManyToOne
    private EmployeeDev employeeTask;




    @JsonIgnore
    @ManyToOne
    private Project projectTask;




}
