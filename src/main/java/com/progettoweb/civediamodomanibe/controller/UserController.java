package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Constants.Endpoint;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.repository.UserRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import com.progettoweb.civediamodomanibe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Users)
public class UserController extends ControllerTemplate<UserDto, UserCriteria, UserService> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService service, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(service);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PutMapping("/disable/{url}")
    public ResponseEntity<UserDto> disableUser(@PathVariable String url) {
        return new ResponseEntity<>(service.disableUser(url), HttpStatus.OK);
    }

    @PutMapping("/enable/{url}")
    public ResponseEntity<UserDto> enableUser(@PathVariable String url) {
        return new ResponseEntity<>(service.enableUser(url), HttpStatus.OK);
    }

    @PutMapping("/{userUrl}/setFavorite/{eventUrl}")
    public ResponseEntity<Boolean> setFavorite(@PathVariable String userUrl, @PathVariable String eventUrl) {
        return new ResponseEntity<>(service.setEventPreferred(userUrl, eventUrl), HttpStatus.OK);
    }

    @PutMapping("/{userUrl}/setAttend/{eventUrl}")
    public ResponseEntity<Boolean> setAttend(@PathVariable String userUrl, @PathVariable String eventUrl) {
        return new ResponseEntity<>(service.setAttendEvent(userUrl, eventUrl), HttpStatus.OK);
    }
}