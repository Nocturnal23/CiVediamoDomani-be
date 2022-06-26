package com.progettoweb.checasavuoibe.service;

import com.progettoweb.checasavuoibe.commons.ServiceTemplate;
import com.progettoweb.checasavuoibe.dto.CategoryDto;
import com.progettoweb.checasavuoibe.entity.Category;
import com.progettoweb.checasavuoibe.repository.CategoryRepository;
import com.progettoweb.checasavuoibe.repository.criteria.CategoryCriteria;
import com.progettoweb.checasavuoibe.repository.specification.CategorySpecificationBuilder;
import com.progettoweb.checasavuoibe.service.mapper.CategoryBidirectionalMapper;
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
