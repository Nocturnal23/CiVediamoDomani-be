package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import com.progettoweb.civediamodomanibe.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends ControllerTemplate<UserDto, UserCriteria, UserService> {

    public UserController(UserService service) {
        super(service);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "UserController works!";
    }
}