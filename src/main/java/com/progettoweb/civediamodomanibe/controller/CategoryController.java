package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.dto.CategoryDto;
import com.progettoweb.civediamodomanibe.repository.criteria.CategoryCriteria;
import com.progettoweb.civediamodomanibe.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController extends ControllerTemplate<CategoryDto, CategoryCriteria, CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }
}