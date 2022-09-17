package com.example.timesheetbackent.security;

import com.example.timesheetbackent.model.Employee;
import com.example.timesheetbackent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Employee employee = userService.findUserByUsername(username);
        if (employee==null)
            throw new UsernameNotFoundException("Utilisateur introuvable!");

        List<GrantedAuthority> auths = new ArrayList<>();

        employee.getRoles().forEach(role -> {
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            auths.add(authority);
        });

        return new org.springframework.security.core.
                userdetails.User(employee.getUsername(), employee.getPassword(), auths);

    }

}
