package com.progettoweb.civediamodomanibe.core.templates;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface BaseJpaRepository<E extends BaseEntity>
        extends JpaSpecificationExecutor<E>, JpaRepository<E, Long> {

    public Optional<E> findByUrl(String url);
}
