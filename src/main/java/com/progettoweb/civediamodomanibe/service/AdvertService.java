package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.commons.ServiceTemplate;
import com.progettoweb.civediamodomanibe.dto.AdvertDto;
import com.progettoweb.civediamodomanibe.entity.Advert;
import com.progettoweb.civediamodomanibe.repository.AdvertRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.AdvertCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.AdvertSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.AdvertBidirectionalMapper;
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
