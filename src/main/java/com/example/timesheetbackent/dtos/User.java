package com.example.timesheetbackent.dtos;

import com.example.timesheetbackent.model.Role;

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<Role> roles;
}
