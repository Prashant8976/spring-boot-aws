package com.example.demo.security;

import org.springframework.stereotype.Service;

import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.GetParameterRequest;
import software.amazon.awssdk.services.ssm.model.GetParameterResponse;

@Service
public class JwtSecretProvider {

    private final SsmClient ssmClient;

    public JwtSecretProvider(SsmClient ssmClient) {
        this.ssmClient = ssmClient;
    }

    public String getSecret() {

        GetParameterResponse response = ssmClient.getParameter(
                GetParameterRequest.builder()
                        .name("/springboot/jwt/secret")
                        .withDecryption(true)
                        .build());

        return response.parameter().value();
    }
}
