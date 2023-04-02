package com.progettoweb.civediamodomanibe.repository;

import com.progettoweb.civediamodomanibe.core.templates.BaseJpaRepository;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseJpaRepository<UserAccount> {

    UserAccount findByEmailIgnoreCase(String email);

}
