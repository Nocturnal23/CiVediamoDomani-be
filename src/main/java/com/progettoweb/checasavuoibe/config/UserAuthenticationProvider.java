package com.progettoweb.checasavuoibe.config;

import com.progettoweb.checasavuoibe.dto.UserDto;
import com.progettoweb.checasavuoibe.dtorequest.CredentialsDto;
import com.progettoweb.checasavuoibe.service.AuthenticationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;

@Component
public class UserAuthenticationProvider {

    @Value("${security.jwt.token.secret-key}")
    private String secretKey;

    private final AuthenticationService authenticationService;

    public UserAuthenticationProvider(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Claims claims = Jwts.claims().setSubject(login);

        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 ora

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication validateToken(String token) {
        String login = Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        UserDto user = authenticationService.findByLogin(login);

        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }

    public Authentication validateCredentials(CredentialsDto credentialsDto) {
        UserDto user = authenticationService.authenticate(credentialsDto);
        return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
    }


}