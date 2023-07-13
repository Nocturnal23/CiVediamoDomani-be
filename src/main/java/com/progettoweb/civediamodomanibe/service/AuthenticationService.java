package com.progettoweb.civediamodomanibe.service;

import com.nimbusds.jose.JOSEException;
import com.progettoweb.civediamodomanibe.core.config.TokenManager;
import com.progettoweb.civediamodomanibe.core.utils.Constants;
import com.progettoweb.civediamodomanibe.core.utils.Utils;
import com.progettoweb.civediamodomanibe.dto.SocialUserDto;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

    @Autowired
    private EmailService emailService;

    public UserDto signUp(UserDto user) {
        if(userService.findByEmail(user.getEmail()) != null)
//            throw new CustomException("Email already registered!", HttpStatus.CONFLICT);
            throw new RuntimeException("Email already registered!");

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userService.registerUser(user);
    }

    public UserDto signIn(UserDto credentials, HttpServletResponse response) {
        UserAccount user = userService.findByEmail(credentials.getEmail());
        if( user == null || !passwordEncoder.matches(credentials.getPassword(), user.getPassword()) || user.getState().equals(Constants.UserState.DISABLED) )
            throw new RuntimeException("Credentials not valid.");

        String email = credentials.getEmail();
        String password = credentials.getPassword();

        //authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        try {
            response.addHeader(HttpHeaders.AUTHORIZATION, TokenManager.getInstance().createToken(Map.of("email",  email)));
            return userService.getMapper().toDto(user);
        } catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserDto googleSignIn(SocialUserDto socialUserDto, HttpServletResponse response) {
        UserAccount userAccount = userService.findByEmail(socialUserDto.getEmail());
        UserDto registeredUser;
        if( userAccount == null ) {
            registeredUser = userService.registerUser(socialUserDto);
        } else {
            if (userAccount.getState().equals(Constants.UserState.DISABLED))
                throw new RuntimeException("Credentials not valid.");

            registeredUser = userService.getMapper().toDto(userAccount);
        }

        try {
            response.addHeader(HttpHeaders.AUTHORIZATION, TokenManager.getInstance().createToken(Map.of("email",  socialUserDto.getEmail())));
            return registeredUser;
        } catch (JOSEException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserDto resetPassword(String email) {
        UserAccount user = userService.findByEmail(email);
        if( user != null ) {
            String password = Utils.generateAlphaNumericString(10);
            user.setPassword( passwordEncoder.encode(password) );
            userService.save(user);
            emailService.sendSimpleMessage( email, "Reset Password", password );
        }

        return null;
    }
}
