package com.progettoweb.checasavuoibe.repository.specification;

import com.progettoweb.checasavuoibe.commons.SpecificationBuilder;
import com.progettoweb.checasavuoibe.entity.Advert;
import com.progettoweb.checasavuoibe.entity.Category;
import com.progettoweb.checasavuoibe.repository.criteria.CategoryCriteria;
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