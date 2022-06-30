package com.progettoweb.checasavuoibe.controller;

import com.progettoweb.checasavuoibe.commons.ControllerTemplate;
import com.progettoweb.checasavuoibe.dto.ReviewDto;
import com.progettoweb.checasavuoibe.repository.criteria.ReviewCriteria;
import com.progettoweb.checasavuoibe.service.ReviewService;
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