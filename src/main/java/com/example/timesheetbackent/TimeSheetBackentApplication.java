package com.example.timesheetbackent;

import com.example.timesheetbackent.model.*;
import com.example.timesheetbackent.repository.EmployeeRepositorie;
import com.example.timesheetbackent.repository.ProjectRepository;
import com.example.timesheetbackent.repository.TaskRepositorie;
import com.example.timesheetbackent.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class TimeSheetBackentApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TimeSheetBackentApplication.class, args);

    }
    @Bean
    BCryptPasswordEncoder getBCE() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserService userService){
        return args -> {
            /*

            userService.addRole(new Role(null,"ADMIN"));
           userService.addRole(new Role(null,"USER"));

            //ajouter les users

            EmployeeManager employeeManager =new EmployeeManager();
            employeeManager.setUsername("mehdi");
            employeeManager.setPassword("123");
            employeeManager.setFirstName("admin");
            employeeManager.setLastName("admin");
            userService.saveEmployeeManger(employeeManager);




            //ajouter les rôles aux users
           userService.addRoleToEmployee("mehdi", "ADMIN");
           userService.addRoleToEmployee("mehdi", "USER");








            EmployeeDev employeeDev =new EmployeeDev();
            employeeDev.setUsername("dev1");
            employeeDev.setPassword("123");
            employeeDev.setFirstName("dev1");
            employeeDev.setLastName("dev1");
            userService.saveEmployeeDev(employeeDev);






            //ajouter les rôles aux users

            userService.addRoleToEmployee("dev1", "USER");
            */






        };
    }
















}
