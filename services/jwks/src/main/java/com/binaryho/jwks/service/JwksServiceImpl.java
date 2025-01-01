package com.binaryho.jwks.service;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.jackson.io.JacksonSerializer;
import io.jsonwebtoken.security.Jwk;
import io.jsonwebtoken.security.JwkSet;
import io.jsonwebtoken.security.Jwks;
import io.jsonwebtoken.security.RsaPrivateJwk;
import io.jsonwebtoken.security.RsaPublicJwk;
import io.jsonwebtoken.security.SecretJwk;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JwksServiceImpl implements JwksService {

    private final SecretJwk hmacSecretJwk;
    private final RsaPrivateJwk rsaPrivateJwk;
    private final RsaPublicJwk rsaPublicJwk;

    public JwksServiceImpl() {
        // 가장 심플한 방법 키 관리 X, Jwks에 직접 추가, 개선 필요
        SecretKey secretKey = Jwts.SIG.HS512.key().build();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) Jwts.SIG.RS512.keyPair().build().getPrivate();

        hmacSecretJwk = Jwks.builder().key(secretKey).idFromThumbprint().build();
        rsaPrivateJwk = Jwks.builder().key(rsaPrivateKey).idFromThumbprint().build();
        rsaPublicJwk = rsaPrivateJwk.toPublicJwk();
    }

    private void error() throws Exception {
        throw new RuntimeException("일부러 만든 에러");
    }

    @Override
    public String getError() {
        try {
            error();
        } catch (Exception e) {
            throw new RuntimeException("에러 발생: " + e.getMessage());
        }
        return "error";
    }

    @Override
    public String getJWKS() {
        JwkSet jwkSet = Jwks.set()
                .add(hmacSecretJwk)
                .add(rsaPublicJwk)
                .add(rsaPrivateJwk)
                // .keys(null)
                // .keys(Collection<Jwk<?>> keys) // 한번에 추가
                .operationPolicy(Jwks.OP
                        .policy()
                        // .add(Jwks.OP.UNWRAP_KEY) // Policy 추가
                        // .add(Jwks.OP.WRAP_KEY) // Policy 추가
                        .build())
                // .provider(Provider provider) // JCA provider 미지정 시 기본 provider 사용
                .build();
        byte[] jwksBytes = new JacksonSerializer<JwkSet>().serialize(jwkSet);
        return new String(jwksBytes, StandardCharsets.UTF_8);
    }

    @Override
    public String getJWKSRsaPublicKeys() {
        JwkSet jwkSet = Jwks.set()
                .add(rsaPublicJwk)
                // .keys(null)
                // .keys(Collection<Jwk<?>> keys) // 한번에 추가
                .operationPolicy(Jwks.OP
                        .policy()
                        // .add(Jwks.OP.UNWRAP_KEY) // Policy 추가
                        // .add(Jwks.OP.WRAP_KEY) // Policy 추가
                        .build())
                // .provider(Provider provider) // JCA provider 미지정 시 기본 provider 사용
                .build();
        byte[] jwksBytes = new JacksonSerializer<JwkSet>().serialize(jwkSet);
        return new String(jwksBytes, StandardCharsets.UTF_8);
    }

    @Override
    public String getJWKSRsaPrivateKeys() {
        JwkSet jwkSet = Jwks.set()
                .add(rsaPrivateJwk)
                // .keys(null)
                // .keys(Collection<Jwk<?>> keys) // 한번에 추가
                .operationPolicy(Jwks.OP
                        .policy()
                        // .add(Jwks.OP.UNWRAP_KEY) // Policy 추가
                        // .add(Jwks.OP.WRAP_KEY) // Policy 추가
                        .build())
                // .provider(Provider provider) // JCA provider 미지정 시 기본 provider 사용
                .build();
        byte[] jwksBytes = new JacksonSerializer<JwkSet>().serialize(jwkSet);
        return new String(jwksBytes, StandardCharsets.UTF_8);
    }
}
