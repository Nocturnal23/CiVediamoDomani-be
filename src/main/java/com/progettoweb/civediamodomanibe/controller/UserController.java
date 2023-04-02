package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.UserRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import com.progettoweb.civediamodomanibe.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends ControllerTemplate<UserDto, UserCriteria, UserService> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService service, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        super(service);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "UserController works!";
    }

    @PostMapping(path="/register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> register(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(userRepository.findByUsername(username) != null)
            return new ResponseEntity<>("existing username", HttpStatus.CONFLICT);
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(passwordEncoder.encode(password));
        userAccount.setDeleted(0L);
        userRepository.save(userAccount);
        return new ResponseEntity<>("registered", HttpStatus.OK);
    }
}