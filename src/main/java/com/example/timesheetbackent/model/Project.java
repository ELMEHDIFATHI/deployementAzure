package com.example.timesheetbackent.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@DynamicUpdate
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Project;
    private String name;

    @Column(length = 5000000,columnDefinition = "LONGTEXT")
    private String description;
    @Column(scale=1)
    private float duration;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String client;

    @JsonIgnore
    @ManyToOne
    @ToString.Exclude
    private EmployeeManager managerProject;






    @ToString.Exclude
    @OneToMany(mappedBy = "projectTask",cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();

    public void setDuration() {
        this.duration = (float) taskList.stream().mapToDouble(t->t.getDuration())
                .sum()/8;
        System.out.println(this.duration);
    }

    public void setStatus() {
        if((!taskList.isEmpty()) && (taskList.stream().allMatch(t->t.isVerified()))){
            this.status = Status.Done;
        }
        else {
            this.status = Status.InProgress;
        }
    }
}
