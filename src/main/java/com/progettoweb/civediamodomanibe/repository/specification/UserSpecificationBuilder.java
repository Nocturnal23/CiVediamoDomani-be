package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserSpecificationBuilder extends SpecificationBuilder<UserAccount, UserCriteria> {
    @Override
    public Specification<UserAccount> filter(UserCriteria criteria) {
        Specification<UserAccount> specification = super.filter(criteria);

        if (criteria.getFirstNameSearch() != null && !criteria.getFirstNameSearch().isEmpty() ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, value, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")),"%" + criteria.getFirstNameSearch().toLowerCase() + "%")
            );
        }

        if ( criteria.getLastNameSearch() != null && !criteria.getLastNameSearch().isEmpty() ) {
            specification = Objects.requireNonNull(specification).and(
                    (root, value, criteriaBuilder) ->
                            criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")),"%" + criteria.getLastNameSearch().toLowerCase() + "%")
            );
        }

        return specification;
    }
}