package com.example.timesheetbackent.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor  @NoArgsConstructor  @Data
public class EmployeeM implements Serializable {


    private Long id;
    private String firstName;
    private String lastName;
    private byte[] photo;
    private String email;



}
