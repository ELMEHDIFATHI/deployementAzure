package com.example.timesheetbackent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Table(name = "Manager")
public class EmployeeManager extends Employee implements Serializable {




    @OneToMany(mappedBy = "managerProject",cascade = CascadeType.ALL)
    private Collection<Project> managerProject = new ArrayList<>();




   @JsonIgnore
    @OneToMany(mappedBy = "employeeManagerEvent",cascade= {CascadeType.REMOVE},fetch = FetchType.EAGER)
    private List<Event> eventList;



}
