package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.dtorequest.CredentialsDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private UserService userService;

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
        return userService.save(user);
    }

//    public UserDto signIn(CredentialsDto credentials) {
//        UserAccount user = userService.findByEmail(credentials.getEmail());
//        if( user == null || passwordEncoder.matches(CharBuffer.wrap(credentials.getPassword()), user.getPassword()) )
//            throw new RuntimeException("Credential not valid.");
//
//        SecurityContextHolder.getContext().setAuthentication();
//    }

    public void signOut(UserDto user) {
        SecurityContextHolder.clearContext();
    }
}
