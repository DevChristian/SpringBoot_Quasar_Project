package com.christian.mendez.quasarfireoperation.service;

import com.christian.mendez.quasarfireoperation.dto.JwtResponseDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtGenerateServiceImpl implements JwtGenerateService{

    @Value("${secretKey}")
    private String secretKey;

    @Override
    public JwtResponseDto getJWTToken(Map<String, String> body) {

        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");

        String token = Jwts
                .builder()
                .setId("christianMendezJWT")
                .setSubject(body.get("username"))
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 90000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return JwtResponseDto.builder().jwtToken("Bearer " + token).build();
    }
}
