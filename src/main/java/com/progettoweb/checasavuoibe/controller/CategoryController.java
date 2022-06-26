package com.progettoweb.checasavuoibe.controller;

import com.progettoweb.checasavuoibe.commons.ControllerTemplate;
import com.progettoweb.checasavuoibe.dto.CategoryDto;
import com.progettoweb.checasavuoibe.repository.criteria.CategoryCriteria;
import com.progettoweb.checasavuoibe.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController extends ControllerTemplate<CategoryDto, CategoryCriteria, CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}