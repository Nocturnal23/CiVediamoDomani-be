package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.commons.ControllerTemplate;
import com.progettoweb.civediamodomanibe.dto.AdvertDto;
import com.progettoweb.civediamodomanibe.repository.criteria.AdvertCriteria;
import com.progettoweb.civediamodomanibe.service.AdvertService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/adverts")
public class AdvertController extends ControllerTemplate<AdvertDto, AdvertCriteria, AdvertService> {

    public AdvertController(AdvertService service) {
        super(service);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}