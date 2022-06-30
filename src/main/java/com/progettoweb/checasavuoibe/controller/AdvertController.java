package com.progettoweb.checasavuoibe.controller;

import com.progettoweb.checasavuoibe.commons.ControllerTemplate;
import com.progettoweb.checasavuoibe.dto.AdvertDto;
import com.progettoweb.checasavuoibe.repository.criteria.AdvertCriteria;
import com.progettoweb.checasavuoibe.service.AdvertService;
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