package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.templates.ServiceTemplate;
import com.progettoweb.civediamodomanibe.dto.EventDto;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.repository.EventRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.EventSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.EventBidirectionalMapper;
import org.springframework.stereotype.Service;

@Service
public class EventService extends ServiceTemplate<Event, EventDto, EventCriteria,
        EventSpecificationBuilder, EventBidirectionalMapper, EventRepository> {
    protected EventService(EventRepository repository, EventBidirectionalMapper mapper, EventSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    protected boolean eligibleToDelete(Long id) {
        return false;
    }

    @Override
    public String getEntityName() {
        return Event.class.getSimpleName();
    }
}