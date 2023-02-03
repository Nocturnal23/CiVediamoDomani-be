package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.commons.ServiceTemplate;
import com.progettoweb.civediamodomanibe.dto.ReviewDto;
import com.progettoweb.civediamodomanibe.entity.Review;
import com.progettoweb.civediamodomanibe.repository.ReviewRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.ReviewCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.ReviewSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.ReviewBidirectionalMapper;
import org.springframework.stereotype.Service;

@Service
public class ReviewService extends ServiceTemplate<Review, ReviewDto, ReviewCriteria,
        ReviewSpecificationBuilder, ReviewBidirectionalMapper, ReviewRepository> {
    protected ReviewService(ReviewRepository repository, ReviewBidirectionalMapper mapper, ReviewSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    protected boolean eligibleToDelete(Long id) {
        return false;
    }

    @Override
    public String getEntityName() {
        return Review.class.getSimpleName();
    }
}
