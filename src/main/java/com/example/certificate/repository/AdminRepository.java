
        package com.example.certificate.repository;

import com.example.certificate.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository
        extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
}
