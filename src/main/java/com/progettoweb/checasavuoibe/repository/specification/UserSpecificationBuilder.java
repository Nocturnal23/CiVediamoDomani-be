package com.progettoweb.checasavuoibe.repository.specification;

import com.progettoweb.checasavuoibe.commons.SpecificationBuilder;
import com.progettoweb.checasavuoibe.entity.User;
import com.progettoweb.checasavuoibe.repository.criteria.UserCriteria;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class UserSpecificationBuilder extends SpecificationBuilder<User, UserCriteria> {
    @Override
    public Specification<User> filter(UserCriteria criteria) {
        return null;
    }
}