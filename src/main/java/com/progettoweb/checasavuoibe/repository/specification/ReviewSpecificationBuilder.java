package com.progettoweb.checasavuoibe.repository.specification;

import com.progettoweb.checasavuoibe.commons.SpecificationBuilder;
import com.progettoweb.checasavuoibe.entity.Review;
import com.progettoweb.checasavuoibe.repository.criteria.ReviewCriteria;
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