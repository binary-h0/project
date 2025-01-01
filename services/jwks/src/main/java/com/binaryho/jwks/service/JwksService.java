package com.binaryho.jwks.service;

public interface JwksService {

    String getJWKS();

    String getJWKSRsaPublicKeys();

    String getJWKSRsaPrivateKeys();

    String getError();
}
