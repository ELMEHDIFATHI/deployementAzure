package com.example.timesheetbackent.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Date start;
    private Date end;
    @JsonIgnore
    @ManyToOne
    private EmployeeManager employeeManagerEvent;
    @JsonIgnore
    @ManyToMany(mappedBy = "eventList")
    private List<EmployeeDev> employeeDevList;

    public void removeEvent(Event event){
        event.getEmployeeDevList().remove(this);
    }
}
