package com.progettoweb.checasavuoibe.repository;

import com.progettoweb.checasavuoibe.commons.BaseJpaRepository;
import com.progettoweb.checasavuoibe.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseJpaRepository<User> {
}
