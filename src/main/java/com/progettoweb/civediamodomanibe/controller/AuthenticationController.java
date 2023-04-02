package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signIn")
    public ResponseEntity<UserDto> signIn(@AuthenticationPrincipal UserDto user) {
        return new ResponseEntity<>(authenticationService.signIn(user), HttpStatus.OK);
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
