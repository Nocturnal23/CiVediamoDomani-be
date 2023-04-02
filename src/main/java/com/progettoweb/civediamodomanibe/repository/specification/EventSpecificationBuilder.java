package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class EventSpecificationBuilder extends SpecificationBuilder<Event, EventCriteria> {
    @Override
    public Specification<Event> filter(EventCriteria criteria) {
        Specification<Event> specification = super.filter(criteria);

        return specification;
    }
}