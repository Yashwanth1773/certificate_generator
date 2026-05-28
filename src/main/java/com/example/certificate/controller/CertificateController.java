package com.example.certificate.controller;

import com.example.certificate.entity.Certificate;
import com.example.certificate.service.CertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/certificate")
@CrossOrigin("*")
public class CertificateController {

    @Autowired
    private CertificateService service;

    @PostMapping("/add")
    public Certificate addCertificate(
            @RequestBody Certificate certificate)
            throws Exception {

        return service.saveCertificate(certificate);
    }

    @GetMapping("/verify/{id}")
    public ResponseEntity<?> verifyCertificate(
            @PathVariable String id) {

        Optional<Certificate> certificate =
                service.verifyCertificate(id);

        if (certificate.isPresent()) {
            return ResponseEntity.ok(certificate.get());
                     }

        return ResponseEntity
                .badRequest()
                .body("Fake Certificate");
    }

    @GetMapping("/all")
    public List<Certificate> getAllCertificates() {
        return service.getAllCertificates();
    }
}