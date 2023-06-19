package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class EventSpecificationBuilder extends SpecificationBuilder<Event, EventCriteria> {
    @Override
    public Specification<Event> filter(EventCriteria criteria) {
        Specification<Event> specification = super.filter(criteria);

        if( criteria.getSearchValue() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    ((root, query, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + criteria.getSearchValue().toLowerCase() + "%"))
            );
        }

        return specification;
    }
} 