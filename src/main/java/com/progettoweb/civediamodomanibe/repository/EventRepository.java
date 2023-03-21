package com.progettoweb.civediamodomanibe.repository;

import com.progettoweb.civediamodomanibe.core.templates.BaseJpaRepository;
import com.progettoweb.civediamodomanibe.entity.Event;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends BaseJpaRepository<Event> {
}
