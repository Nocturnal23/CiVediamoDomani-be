package com.progettoweb.checasavuoibe.repository.specification;

import com.progettoweb.checasavuoibe.commons.SpecificationBuilder;
import com.progettoweb.checasavuoibe.entity.Advert;
import com.progettoweb.checasavuoibe.repository.criteria.AdvertCriteria;
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