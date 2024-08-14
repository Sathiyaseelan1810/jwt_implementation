package com.spring.jwt.implementation.jwt.service.impl;

import com.spring.jwt.implementation.jwt.entity.AuthenticateEntity;
import com.spring.jwt.implementation.jwt.repository.AuthenticateJwtRepo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AuthenticateService {
    @Autowired
    private AuthenticateJwtRepo authenticateJwtRepo;

    @PostConstruct
    public void initUsers()
    {
        List<AuthenticateEntity> authenticateEntityList = Stream.of(
                        new AuthenticateEntity(100, "sathiya", "pwd1", "pwd1@gmail.com"),
                        new AuthenticateEntity(101, "seelan", "pwd2", "pwd2@gmail.com"),
                        new AuthenticateEntity(102, "asrith", "pwd3", "pwd3@gmail.com"),
                        new AuthenticateEntity(103, "adyukth", "pwd4", "pwd4@gmail.com"))
                .collect(Collectors.toList());
        authenticateJwtRepo.saveAll(authenticateEntityList);
    }


    // Hex 256 bit/32 byte encrypted key
    private static final String SECRET = "8cad45637e87716cf025a246b86d6ddb4df49d1d89e817b09c0e4b50e2d80c72" ;

    /*
        Claims = Header, Payload and verify signature as part of the JWT sections
     */
    public String generateToken(String userName){
        Map<String, Object> claimsToken=  new HashMap<>();
        return createToken(claimsToken, userName);
    }

    private String createToken(Map<String, Object> claimsToken, String userName) {
        // api, impl, jackson - Dependencies..
        return Jwts.builder()
                .setClaims(claimsToken)
                .setSubject(userName)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*30)) // 30 Minutes
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact(); // "Step 1: Header and verify Signature included in this mechanism"
    }

    private Key getSignKey() {
        // Allkeysgenerator = Site Link
        // Encryption Key = 256 bit
        // Format = Hex
        byte[] keyInBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyInBytes);
    }


//    public List<AuthenticateEntity> getAllProducts() {
//
//
//    }
}
