package com.progettoweb.civediamodomanibe.commons;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
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

        return specification;
    }

    public Specification<E> buildSpecification() {
        return Specification.where(null);
    }


    protected <T> Specification<E> equalsSpecification(String fieldName, T value) {
        return (root, query, builder) -> builder.equal(root.get(fieldName), value);
    }

    protected <T> Specification<E> equalsSpecification(Long fieldName, T value) {
        return (root, query, builder) -> builder.equal(root.get(String.valueOf(fieldName)), value);
    }

    protected Specification<E> likeUpperSpecification(String fieldName, String value) {
        return (root, query, builder) ->
                builder.like(builder.upper(root.get(fieldName)), wrapLikeQuery(value));
    }

    protected Specification<E> likeLowerSpecification(String fieldName, String value) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get(fieldName)), wrapLikeQuery(value));
    }

    protected static String wrapLikeQuery(String txt) {
        return "%" + txt.toLowerCase() + "%";
    }


}
