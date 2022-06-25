package com.progettoweb.checasavuoibe.service;

import com.progettoweb.checasavuoibe.commons.ServiceTemplate;
import com.progettoweb.checasavuoibe.dto.ReviewDto;
import com.progettoweb.checasavuoibe.entity.Review;
import com.progettoweb.checasavuoibe.repository.ReviewRepository;
import com.progettoweb.checasavuoibe.repository.criteria.ReviewCriteria;
import com.progettoweb.checasavuoibe.repository.specification.ReviewSpecificationBuilder;
import com.progettoweb.checasavuoibe.service.mapper.ReviewBidirectionalMapper;
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
