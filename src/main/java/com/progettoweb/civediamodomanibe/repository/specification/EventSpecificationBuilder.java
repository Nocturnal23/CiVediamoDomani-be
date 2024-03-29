package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.Category;
import com.progettoweb.civediamodomanibe.entity.Event;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.criteria.EventCriteria;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Component
public class EventSpecificationBuilder extends SpecificationBuilder<Event, EventCriteria> {
    @Override
    public Specification<Event> filter(EventCriteria criteria) {
        Specification<Event> specification = super.filter(criteria);

        if( criteria.getEventTitle() != null && !criteria.getEventTitle().isEmpty() ) {
            specification = Objects.requireNonNull(specification).and(
                    ((root, query, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), "%" + criteria.getEventTitle().get(0).toLowerCase() + "%"))
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

        if( criteria.getCategories() != null && !criteria.getCategories().isEmpty() ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, query, criteriaBuilder) -> {
                        Join<Event, Category> join = root.join( "categories", JoinType.LEFT );
                        CriteriaBuilder.In<String> in = criteriaBuilder.in(criteriaBuilder.lower(join.get("name")));
                        for (String value : criteria.getCategories()) {
                            in = in.value(value.toLowerCase());
                        }
                        return in;
                    }
            );
        }

        if(criteria.getEventDate() != null && !criteria.getEventDate().isEmpty()){
            LocalDateTime ldt = LocalDateTime.parse(criteria.getEventDate().get(0), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            specification = Objects.requireNonNull(specification).and(
                    (((root, criteriaQuery, criteriaBuilder) ->
                            criteriaBuilder.greaterThanOrEqualTo(root.get("datetime"), ldt)))
            );
        }


        return specification;
    }
} 