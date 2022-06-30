package com.progettoweb.checasavuoibe.controller;

import com.progettoweb.checasavuoibe.commons.ControllerTemplate;
import com.progettoweb.checasavuoibe.dto.UserDto;
import com.progettoweb.checasavuoibe.repository.criteria.UserCriteria;
import com.progettoweb.checasavuoibe.service.UserService;
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