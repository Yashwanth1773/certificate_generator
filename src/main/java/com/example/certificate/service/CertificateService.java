package com.example.certificate.service;

import com.example.certificate.entity.Certificate;
import com.example.certificate.repository.CertificateRepository;
import com.example.certificate.util.QRCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CertificateService {

    @Autowired
    private CertificateRepository repository;

    public Certificate saveCertificate(
            Certificate certificate)
            throws Exception {

        String uniqueId =
                UUID.randomUUID().toString();

        certificate.setCertificateId(uniqueId);

        String verifyUrl =
                "http://localhost:8080/api/certificate/verify/"
                        + uniqueId;

        File folder = new File("qrcodes");

        if (!folder.exists()) {
            folder.mkdir();
        }

        String qrPath =
                "qrcodes/" + uniqueId + ".png";

        QRCodeGenerator.generateQRCode(
                verifyUrl,
                qrPath);

        certificate.setQrCodePath(qrPath);

        return repository.save(certificate);
    }

    public Optional<Certificate> verifyCertificate(
            String certificateId) {

        return repository.findByCertificateId(
                certificateId);
    }

    public List<Certificate> getAllCertificates() {
        return repository.findAll();
    }
}