package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Category;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
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

        if( criteria.getOrganiserId() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, query, criteriaBuilder) ->
                            criteriaBuilder.equal( root.get("organiser").get("id"), criteria.getOrganiserId() )
            );
        }

        if( criteria.getFollowerId() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, query, criteriaBuilder) -> {
                        Join<Event, UserAccount> join = root.join( "followers", JoinType.LEFT );
                        return criteriaBuilder.equal( join.get("id"), criteria.getFollowerId() );
                    }
            );
        }

        if( criteria.getAttendeeId() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, query, criteriaBuilder) -> {
                        Join<Event, UserAccount> join = root.join( "attendees", JoinType.LEFT );
                        return criteriaBuilder.equal( join.get("id"), criteria.getAttendeeId() );
                    }
            );
        }

        if( criteria.getCategory() != null ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, query, criteriaBuilder) -> {
                        Join<Event, Category> join = root.join( "categories", JoinType.LEFT );
                        return criteriaBuilder.like(criteriaBuilder.lower(join.get("name")), "%" + criteria.getCategory().toLowerCase() + "%");
                    }
            );
        }


        return specification;
    }
} 