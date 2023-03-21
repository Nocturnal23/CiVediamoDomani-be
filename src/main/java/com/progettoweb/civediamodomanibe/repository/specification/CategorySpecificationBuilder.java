package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Category;
import com.progettoweb.civediamodomanibe.repository.criteria.CategoryCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class CategorySpecificationBuilder extends SpecificationBuilder<Category, CategoryCriteria> {
    @Override
    public Specification<Category> filter(CategoryCriteria criteria) {
        Specification<Category> specification = super.filter(criteria);

        return specification;
    }
}