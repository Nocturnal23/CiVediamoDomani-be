package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.config.UserAuthenticationProvider;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.dtorequest.CredentialsDto;
import com.progettoweb.civediamodomanibe.entity.User;
import com.progettoweb.civediamodomanibe.core.exception.RestrictedActionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.nio.CharBuffer;

@RequiredArgsConstructor
@Service
@Slf4j
public class AuthenticationService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserAuthenticationProvider userAuthenticationProvider;

    @Autowired
    private UserService userService;

    @Transactional
    public UserDto authenticate(CredentialsDto credentialsDto) {
        User user = userService.findByEmail(credentialsDto.getEmail())
                .orElseThrow(() -> new RestrictedActionException(HttpStatus.NOT_FOUND.name() + "User not found"));

//        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPasswordDigest())) {
//            log.debug("User {} authenticated correctly", credentialsDto.getEmail());
//            return userService.getMapper().toDto(user);
//        }
        throw new RestrictedActionException(HttpStatus.BAD_REQUEST.name() + "Invalid password");
    }

    public UserDto findByLogin(String email) {
        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RestrictedActionException(HttpStatus.NOT_FOUND.name() + "User not found"));
        return userService.getMapper().toDto(user);
    }

    public UserDto signIn(UserDto user) {
//        user.setToken(userAuthenticationProvider.createToken(user.getEmail()));
        return null; //TODO Da sistemare
    }

    public void signOut(UserDto user) {
        SecurityContextHolder.clearContext();
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder();
    }
}
