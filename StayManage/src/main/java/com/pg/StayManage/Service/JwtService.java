package com.pg.StayManage.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String key;

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expiry-time}")
    private int expiry;

    private Algorithm algorithm;

    @PostConstruct
    public void postConstruct() throws UnsupportedEncodingException {
        algorithm = Algorithm.HMAC256(key);
    }

    public String generateToken(String username, String role) {
        return JWT.create()
                .withClaim("name", username)
                .withClaim("role", role)
                .withExpiresAt(new Date(System.currentTimeMillis() + expiry))
                .withIssuer(issuer)
                .sign(algorithm);
    }

    public String getUserName(String token) {
        DecodedJWT decodedJWT = JWT.require(algorithm)
                .withIssuer(issuer)
                .build()
                .verify(token);
        return decodedJWT.getClaim("name").asString();
    }
}
