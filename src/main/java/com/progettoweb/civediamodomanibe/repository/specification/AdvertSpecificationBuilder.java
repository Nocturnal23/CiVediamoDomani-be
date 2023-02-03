package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.commons.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Advert;
import com.progettoweb.civediamodomanibe.repository.criteria.AdvertCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class AdvertSpecificationBuilder extends SpecificationBuilder<Advert, AdvertCriteria> {
    @Override
    public Specification<Advert> filter(AdvertCriteria criteria) {
        Specification<Advert> specification = super.filter(criteria);

        return specification;
    }
}