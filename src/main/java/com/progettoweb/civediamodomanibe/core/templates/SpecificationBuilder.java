package com.progettoweb.civediamodomanibe.core.templates;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public abstract class SpecificationBuilder<E extends BaseEntity, C extends BaseCriteria> {

    public Specification<E> filter(C criteria) {
        Specification<E> specification = Specification.where(null);

        if (criteria.getIds() != null && !criteria.getIds().isEmpty()) {
            specification = Objects.requireNonNull(specification).and(
                    ((root, query, criteriaBuilder) -> {
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("id"));
                        for (Long value : criteria.getIds()) {
                            in = in.value(value);
                        }
                        return in;
                    }));
        }

        if (criteria.getExcludedIds() != null && !criteria.getExcludedIds().isEmpty()) {
            specification = Objects.requireNonNull(specification).and(
                    ((root, query, criteriaBuilder) -> {
                        CriteriaBuilder.In<Long> in = criteriaBuilder.in(root.get("id"));
                        for (Long value : criteria.getExcludedIds()) {
                            in = in.value(value);
                        }
                        return criteriaBuilder.not(in);
                    }));
        }

        if(criteria.getStartingDate() != null && !criteria.getStartingDate().equals("")){
            LocalDateTime startingDate = LocalDateTime.parse(criteria.getStartingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            specification = Objects.requireNonNull(specification).and(
                    (((root, criteriaQuery, criteriaBuilder) ->
                            criteriaBuilder.greaterThanOrEqualTo(root.get("createdDate"), startingDate)))
            );
        }

        if(criteria.getEndingDate() != null && !criteria.getEndingDate().equals("")){
            LocalDateTime endingDate = LocalDateTime.parse(criteria.getEndingDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            specification = Objects.requireNonNull(specification).and(
                    (((root, criteriaQuery, criteriaBuilder) ->
                            criteriaBuilder.lessThanOrEqualTo(root.get("createdDate"), endingDate)))
            );
        }

        return specification;
    }

    protected static String wrapLikeQuery(String txt) {
        return "%" + txt.toLowerCase() + "%";
    }


}
