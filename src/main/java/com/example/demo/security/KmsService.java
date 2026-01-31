package com.example.demo.security;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.DecryptRequest;
import software.amazon.awssdk.services.kms.model.DecryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptResponse;

@Service
@RequiredArgsConstructor
public class KmsService {

    private final KmsClient kmsClient;

    private final JwtSecretProvider jwtSecretProvider;
    @Value("${app.kms.key-arn}")
    private String keyArn;


    public String getDecryptedSecret() {
        DecryptResponse res = kmsClient.decrypt(
                DecryptRequest.builder()
                        .ciphertextBlob(SdkBytes.fromByteArray(
								Base64.getDecoder().decode((jwtSecretProvider.getSecret()))))
                        .build());
        return res.plaintext().asUtf8String();
    }

    public String encrypt(String text) {
        EncryptResponse res = kmsClient.encrypt(
                EncryptRequest.builder()
                        .keyId(keyArn)
                        .plaintext(SdkBytes.fromUtf8String(text))
                        .build());
        return Base64.getEncoder()
                .encodeToString(res.ciphertextBlob().asByteArray());
    }
    
    public String decrypt(String cipherText) {

        DecryptResponse response = kmsClient.decrypt(
                DecryptRequest.builder()
                        .ciphertextBlob(
                                SdkBytes.fromByteArray(
                                        Base64.getDecoder().decode(cipherText)
                                )
                        )
                        .build()
        );

        return response.plaintext().asUtf8String();
    }

}
