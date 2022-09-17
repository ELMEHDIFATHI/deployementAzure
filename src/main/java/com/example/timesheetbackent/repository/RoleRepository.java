package com.example.timesheetbackent.repository;

import com.example.timesheetbackent.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByRole(String role);
}
