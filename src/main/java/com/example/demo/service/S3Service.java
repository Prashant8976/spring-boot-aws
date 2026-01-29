package com.example.demo.service;

import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import io.awspring.cloud.s3.S3Template;

@Service
public class S3Service {

    private final S3Template s3Template;
    private final String bucketName = "";

    public S3Service(S3Template s3Template) {
        this.s3Template = s3Template;
    }

    public void uploadFile(MultipartFile file) throws IOException {
        s3Template.upload(bucketName, file.getOriginalFilename(), file.getInputStream());
    }

    public byte[] downloadFile(String fileName) throws IOException {
        return s3Template.download(bucketName, fileName).getContentAsByteArray();
    }
}
