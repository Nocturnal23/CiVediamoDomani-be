package com.progettoweb.civediamodomanibe.service;

import com.nimbusds.jose.JOSEException;
import com.progettoweb.civediamodomanibe.core.config.TokenManager;
import com.progettoweb.civediamodomanibe.core.utils.Constants;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.dtorequest.CredentialsDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import java.nio.CharBuffer;
import java.util.Arrays;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


//    @Transactional
//    public UserDto authenticate(CredentialsDto credentialsDto) {
////        User user = userService.findByEmail(credentialsDto.getEmail())
////                .orElseThrow(() -> new RestrictedActionException(HttpStatus.NOT_FOUND.name() + "User not found"));
//
////        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPasswordDigest())) {
////            log.debug("User {} authenticated correctly", credentialsDto.getEmail());
////            return userService.getMapper().toDto(user);
////        }
////        throw new RestrictedActionException(HttpStatus.BAD_REQUEST.name() + "Invalid password");
//        return null;
//    }

    public UserDto signUp(UserDto user) {
        if(userService.findByEmail(user.getEmail()) != null)
//            throw new CustomException("Email already registered!", HttpStatus.CONFLICT);
            throw new RuntimeException("Email already registered!");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAppRole(Constants.Role.NORMAL);
        return userService.save(user);
    }

    public UserDto signIn(UserDto credentials, HttpServletResponse response) {
        UserAccount user = userService.findByEmail(credentials.getEmail());
        if( user == null || !passwordEncoder.matches(credentials.getPassword(), user.getPassword()) )
            throw new RuntimeException("Credentials not valid.");

        //SecurityContextHolder.getContext().setAuthentication();

        String email = credentials.getEmail();
        String password = credentials.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        try {
            response.addHeader(HttpHeaders.AUTHORIZATION, TokenManager.getInstance().createToken(Map.of("email",  email)));
            return userService.getMapper().toDto(user);
        } catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void signOut(UserDto user) {
        SecurityContextHolder.clearContext();
    }
}
