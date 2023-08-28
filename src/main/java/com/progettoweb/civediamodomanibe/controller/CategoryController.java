package com.progettoweb.civediamodomanibe.controller;

import com.progettoweb.civediamodomanibe.core.templates.ControllerTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Constants.Endpoint;
import com.progettoweb.civediamodomanibe.dto.CategoryDto;
import com.progettoweb.civediamodomanibe.repository.criteria.CategoryCriteria;
import com.progettoweb.civediamodomanibe.service.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Endpoint.Categories)
public class CategoryController extends ControllerTemplate<CategoryDto, CategoryCriteria, CategoryService> {

    public CategoryController(CategoryService service) {
        super(service);
    }

}