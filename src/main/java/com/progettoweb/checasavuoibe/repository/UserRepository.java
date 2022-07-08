package com.progettoweb.checasavuoibe.repository;

import com.progettoweb.checasavuoibe.commons.BaseJpaRepository;
import com.progettoweb.checasavuoibe.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseJpaRepository<User> {

    Optional<User> findByEmailIgnoreCase(String email);
}
