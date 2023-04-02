package com.progettoweb.civediamodomanibe.repository.specification;

import com.progettoweb.civediamodomanibe.core.templates.SpecificationBuilder;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationBuilder extends SpecificationBuilder<UserAccount, UserCriteria> {
    @Override
    public Specification<UserAccount> filter(UserCriteria criteria) {
        Specification<UserAccount> specification = super.filter(criteria);

        return specification;
    }
}