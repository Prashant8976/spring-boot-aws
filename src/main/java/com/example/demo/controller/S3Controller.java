package com.example.demo.controller;

import com.example.demo.service.S3Service;

import java.io.IOException;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/s3")
public class S3Controller {

    private final S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) throws IOException {
        s3Service.uploadFile(file);
        return "File uploaded successfully";
    }

    @GetMapping("/download/{fileName}")
    public byte[] download(@PathVariable String fileName) throws IOException {
        return s3Service.downloadFile(fileName);
    }
}
