package com.progettoweb.civediamodomanibe.repository;

import com.progettoweb.civediamodomanibe.core.templates.BaseJpaRepository;
import com.progettoweb.civediamodomanibe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseJpaRepository<User> {

    Optional<User> findByEmailIgnoreCase(String email);
}
