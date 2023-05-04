package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.dtorequest.CredentialsDto;
import com.progettoweb.civediamodomanibe.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signIn")
    @ResponseStatus(HttpStatus.OK)
    public void signIn(@RequestBody UserDto credentials, HttpServletResponse response) {
        response.addHeader(HttpHeaders.AUTHORIZATION, authenticationService.signIn(credentials));

        //return new ResponseEntity<>(authenticationService.signIn(credentials), HttpStatus.OK);
    }

    @PostMapping("/signUp")
    public ResponseEntity<UserDto> signUp(@RequestBody UserDto user) {
        return new ResponseEntity<>(authenticationService.signUp(user), HttpStatus.OK);
    }

//    @PostMapping("/signOut")
//    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
//        authenticationService.signOut(user);
//        return ResponseEntity.noContent().build();
//    }
}
