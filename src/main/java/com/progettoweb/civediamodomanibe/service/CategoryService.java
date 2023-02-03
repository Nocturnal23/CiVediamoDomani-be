package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.commons.ServiceTemplate;
import com.progettoweb.civediamodomanibe.dto.CategoryDto;
import com.progettoweb.civediamodomanibe.entity.Category;
import com.progettoweb.civediamodomanibe.repository.CategoryRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.CategoryCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.CategorySpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.CategoryBidirectionalMapper;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends ServiceTemplate<Category, CategoryDto, CategoryCriteria,
        CategorySpecificationBuilder, CategoryBidirectionalMapper, CategoryRepository> {
    protected CategoryService(CategoryRepository repository, CategoryBidirectionalMapper mapper, CategorySpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    protected boolean eligibleToDelete(Long id) {
        return false;
    }

    @Override
    public String getEntityName() {
        return Category.class.getSimpleName();
    }
}
