package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.templates.ServiceTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Utils;
import com.progettoweb.civediamodomanibe.dto.CategoryDto;
import com.progettoweb.civediamodomanibe.entity.Category;
import com.progettoweb.civediamodomanibe.repository.CategoryRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.CategoryCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.CategorySpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.CategoryBidirectionalMapper;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends ServiceTemplate<Category, CategoryDto, CategoryCriteria,
        CategorySpecificationBuilder, CategoryBidirectionalMapper, CategoryRepository> {
    protected CategoryService(CategoryRepository repository, CategoryBidirectionalMapper mapper, CategorySpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    public String getEntityName() {
        return Category.class.getSimpleName();
    }

    @Override
    public Category save(@NotNull Category entity) {
        entity.setUrl(Utils.generateString(6L));
        return super.save(entity);
    }
}
