package com.progettoweb.civediamodomanibe.service;

import com.progettoweb.civediamodomanibe.core.templates.ServiceTemplate;
import com.progettoweb.civediamodomanibe.core.utils.Constants;
import com.progettoweb.civediamodomanibe.core.utils.Utils;
import com.progettoweb.civediamodomanibe.dto.SocialUserDto;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import com.progettoweb.civediamodomanibe.repository.UserRepository;
import com.progettoweb.civediamodomanibe.repository.criteria.UserCriteria;
import com.progettoweb.civediamodomanibe.repository.specification.UserSpecificationBuilder;
import com.progettoweb.civediamodomanibe.service.mapper.UserBidirectionalMapper;
import org.springframework.stereotype.Service;


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

    public UserAccount findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }

    public UserDto registerUser (UserDto newUser) {
        newUser.setAppRole(Constants.Role.NORMAL);
        newUser.setUrl(Utils.generateString(8L));
        return save(newUser);
    }

    public UserDto registerUser (SocialUserDto newUser) {
        UserDto user = new UserDto();
        user.setEmail(newUser.getEmail());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        return registerUser(user);
    }
}
