package com.progettoweb.checasavuoibe.service;

import com.progettoweb.checasavuoibe.commons.ServiceTemplate;
import com.progettoweb.checasavuoibe.dto.UserDto;
import com.progettoweb.checasavuoibe.entity.User;
import com.progettoweb.checasavuoibe.repository.UserRepository;
import com.progettoweb.checasavuoibe.repository.criteria.UserCriteria;
import com.progettoweb.checasavuoibe.repository.specification.UserSpecificationBuilder;
import com.progettoweb.checasavuoibe.service.mapper.UserBidirectionalMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceTemplate<User, UserDto, UserCriteria,
        UserSpecificationBuilder, UserBidirectionalMapper, UserRepository> {
    protected UserService(UserRepository repository, UserBidirectionalMapper mapper, UserSpecificationBuilder specificationBuilder) {
        super(repository, mapper, specificationBuilder);
    }

    @Override
    protected boolean eligibleToDelete(Long id) {
        return false;
    }

    @Override
    public String getEntityName() {
        return User.class.getSimpleName();
    }

    public Optional<User> findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }
}
