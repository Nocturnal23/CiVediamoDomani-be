package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.templates.ServiceTemplate;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.UserRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.UserSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.UserBidirectionalMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends ServiceTemplate<UserAccount, UserDto, UserCriteria,
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
        return UserAccount.class.getSimpleName();
    }

    public Optional<UserAccount> findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }
}
