package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.commons.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.User;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationBuilder extends SpecificationBuilder<User, UserCriteria> {
    @Override
    public Specification<User> filter(UserCriteria criteria) {
        Specification<User> specification = super.filter(criteria);

        return specification;
    }
}