package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.commons.ControllerTemplate;
import com.progettoweb.civediamodomanibe.dto.ReviewDto;
import com.progettoweb.civediamodomanibe.repository.criteria.ReviewCriteria;
import com.progettoweb.civediamodomanibe.service.ReviewService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController extends ControllerTemplate<ReviewDto, ReviewCriteria, ReviewService> {

    public ReviewController(ReviewService service) {
        super(service);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "ReviewController works!";
    }
}