package com.example.crud.Config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Component
@Data
@ConfigurationProperties(prefix = "rsa")
public class RsaKeyConfigProperties {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;

}