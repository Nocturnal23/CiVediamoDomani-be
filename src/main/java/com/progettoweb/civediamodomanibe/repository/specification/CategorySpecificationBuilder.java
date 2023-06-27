package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Category;
import com.progettoweb.civediamodomanibe.repository.criteria.CategoryCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import java.util.Objects;

@Component
public class CategorySpecificationBuilder extends SpecificationBuilder<Category, CategoryCriteria> {
    @Override
    public Specification<Category> filter(CategoryCriteria criteria) {
        Specification<Category> specification = super.filter(criteria);

        if( criteria.getFatherID() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.equal( root.get("father").get("id"), criteria.getFatherID() )
            );
        }

        if( criteria.getChildCategorySearch() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    ((root, query, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + criteria.getChildCategorySearch().toLowerCase() + "%"))
            );
        }

        return specification;
    }
}