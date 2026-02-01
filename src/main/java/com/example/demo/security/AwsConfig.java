package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.ssm.SsmClient;

@Configuration
public class AwsConfig {

    @Bean
    KmsClient kmsClient() {
        return KmsClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    @Bean
    S3Client s3Client() {
        return S3Client.builder()
                .region(Region.US_EAST_1)
                .build();
    }
    
    @Bean
    SsmClient ssmClient() {
        return SsmClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }
}