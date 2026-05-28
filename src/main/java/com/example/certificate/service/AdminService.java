
        package com.example.certificate.service;

import com.example.certificate.entity.Admin;
import com.example.certificate.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    // REGISTER
    public Admin register(Admin admin) {

        return adminRepository.save(admin);
    }

    // LOGIN
    public boolean login(Admin admin) {

        Admin existingUser =
                adminRepository.findByUsername(
                        admin.getUsername());

        if(existingUser != null &&
                existingUser.getPassword()
                        .equals(admin.getPassword())) {

            return true;
        }

        return false;
    }
}

