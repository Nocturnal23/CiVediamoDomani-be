package com.progettoweb.checasavuoibe.service;

import com.progettoweb.checasavuoibe.commons.ServiceTemplate;
import com.progettoweb.checasavuoibe.dto.AdvertDto;
import com.progettoweb.checasavuoibe.entity.Advert;
import com.progettoweb.checasavuoibe.repository.AdvertRepository;
import com.progettoweb.checasavuoibe.repository.criteria.AdvertCriteria;
import com.progettoweb.checasavuoibe.repository.specification.AdvertSpecificationBuilder;
import com.progettoweb.checasavuoibe.service.mapper.AdvertBidirectionalMapper;
import org.springframework.stereotype.Service;

@Service
public class AdvertService extends ServiceTemplate<Advert, AdvertDto, AdvertCriteria,
        AdvertSpecificationBuilder, AdvertBidirectionalMapper, AdvertRepository> {
    protected AdvertService(AdvertRepository repository, AdvertBidirectionalMapper mapper, AdvertSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    protected boolean eligibleToDelete(Long id) {
        return false;
    }

    @Override
    public String getEntityName() {
        return Advert.class.getSimpleName();
    }
}
