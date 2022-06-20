package com.progettoweb.checasavuoibe.service;

import com.progettoweb.checasavuoibe.commons.ServiceTemplate;
import com.progettoweb.checasavuoibe.dto.UserDto;
import com.progettoweb.checasavuoibe.entity.User;
import com.progettoweb.checasavuoibe.repository.UserRepository;
import com.progettoweb.checasavuoibe.repository.criteria.UserCriteria;
import com.progettoweb.checasavuoibe.repository.specification.UserSpecificationBuilder;
import com.progettoweb.checasavuoibe.service.mapper.UserBidirectionalMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceTemplate<User, UserDto, UserCriteria,
        UserSpecificationBuilder, UserBidirectionalMapper, UserRepository> {
    protected UserService(UserRepository repository, UserBidirectionalMapper mapper, UserSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    public String[] getHeaders() {
        return new String[0];
    }

    @Override
    public String[] populate(User entity) {
        return new String[0];
    }

    @Override
    protected boolean eligibleToDelete(Long id) {
        return false;
    }

    @Override
    public String getEntityName() {
        return null;
    }
}
