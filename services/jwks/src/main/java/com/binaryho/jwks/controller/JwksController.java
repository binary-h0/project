package com.binaryho.jwks.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.binaryho.global.dto.Result;
import com.binaryho.jwks.service.JwksService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@RequestMapping("/jwks")
@RequiredArgsConstructor
public class JwksController {

    private final JwksService jwksService;

    @GetMapping
    public ResponseEntity<Result<?>> getJWKS() {
        jwksService.getJWKS();
        return ResponseEntity.ok().body(Result.success(jwksService.getJWKS()));
    }

    @GetMapping("/rsa/public")
    public ResponseEntity<Result<?>> getJWKSRsaPublicKeys() {
        return ResponseEntity.ok().body(Result.success(jwksService.getJWKSRsaPublicKeys()));
    }

    @GetMapping("/rsa/private")
    public ResponseEntity<Result<?>> getJWKSRsaPrivateKeys() {
        return ResponseEntity.ok().body(Result.success(jwksService.getJWKSRsaPrivateKeys()));
    }

    @GetMapping("/error")
    public ResponseEntity<Result<?>> getError() {
        return ResponseEntity.ok().body(Result.success(jwksService.getError()));
    }
}
