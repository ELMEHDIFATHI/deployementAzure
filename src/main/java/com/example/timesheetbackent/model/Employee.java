package com.example.timesheetbackent.model;


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
import java.util.List;



@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
@AllArgsConstructor  @NoArgsConstructor  @Data
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    @Lob
    private byte[] photo;
    private String email;


    @ManyToMany(cascade= {CascadeType.REMOVE},fetch = FetchType.EAGER)
    @JoinTable(name = "employee_role",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Role> roles = new ArrayList<>();


}
