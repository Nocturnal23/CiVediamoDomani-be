package com.progettoweb.checasavuoibe.controller;

import com.progettoweb.checasavuoibe.dto.UserDto;
import com.progettoweb.checasavuoibe.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

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

//    @PostMapping("/signUp")
//    public ResponseEntity<UserDto> signUp(@RequestBody @Valid SignUpDto user) {
//        UserDto createdUser = userService.signUp(user);
//        return ResponseEntity.created(URI.create("/users/" + createdUser.getId() + "/profile")).body(createdUser);
//    }

    @PostMapping("/signOut")
    public ResponseEntity<Void> signOut(@AuthenticationPrincipal UserDto user) {
        authenticationService.signOut(user);
        return ResponseEntity.noContent().build();
    }
}
