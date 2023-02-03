package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.commons.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Review;
import com.progettoweb.civediamodomanibe.repository.criteria.ReviewCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class ReviewSpecificationBuilder extends SpecificationBuilder<Review, ReviewCriteria> {
    @Override
    public Specification<Review> filter(ReviewCriteria criteria) {
        Specification<Review> specification = super.filter(criteria);

        return specification;
    }
}