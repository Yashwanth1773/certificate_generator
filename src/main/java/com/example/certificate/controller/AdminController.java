package com.example.certificate.controller;

import com.example.certificate.entity.Admin;
import com.example.certificate.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin("*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public Admin register(@RequestBody Admin admin) {

        return adminService.register(admin);
    }

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {

        boolean isValid =
                adminService.login(admin);

        if(isValid) {

            return "Login Success";
        }
        else {

            return "Invalid Credentials";
        }
    }
}