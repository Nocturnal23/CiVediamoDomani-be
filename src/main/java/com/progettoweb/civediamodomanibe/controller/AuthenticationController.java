package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.utils.Constants.Endpoint;
import com.progettoweb.civediamodomanibe.dto.SocialUserDto;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping(Endpoint.Authentication)
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@RequestBody UserDto credentials, HttpServletResponse response) {
        return new ResponseEntity<>(authenticationService.signIn(credentials, response), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto user) {
        return new ResponseEntity<>(authenticationService.signUp(user), HttpStatus.OK);
    }

    @PostMapping("/googleSignIn")
    public ResponseEntity<UserDto> googleSignIn(@RequestBody SocialUserDto user, HttpServletResponse response) {
        return new ResponseEntity<>(authenticationService.googleSignIn(user, response), HttpStatus.OK);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<UserDto> resetPassword(@RequestBody String email) {
        return new ResponseEntity<>(authenticationService.resetPassword(email), HttpStatus.OK);
    }
}
