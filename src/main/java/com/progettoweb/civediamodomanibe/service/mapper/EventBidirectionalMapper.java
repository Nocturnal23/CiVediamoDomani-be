package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.core.templates.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.EventDto;
import com.progettoweb.civediamodomanibe.entity.Event;
import org.mapstruct.*;

import java.util.Collections;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        imports = {Collections.class, Collectors.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserBidirectionalMapper.class, CategoryBidirectionalMapper.class})
public interface EventBidirectionalMapper extends BidirectionalMapper<EventDto, Event> {

    @Override
    @Mapping(target = "attendees", expression = "java(entity.getAttendees() == null ? Collections.singletonList(\"\") : entity.getAttendees().stream().map(userAccount -> userAccount.getUrl()).collect(Collectors.toList()))")
    @Mapping(target = "followers", expression = "java(entity.getFollowers() == null ? Collections.singletonList(\"\") : entity.getFollowers().stream().map(userAccount -> userAccount.getUrl()).collect(Collectors.toList()))")
    EventDto toDto(Event entity);

    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.deleted", target = "deleted")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "organiser", ignore = true)
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.place", target = "place")
    @Mapping(source = "dto.coordinates", target = "coordinates")
    @Mapping(source = "dto.datetime", target = "datetime")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.price", target = "price")
    @Mapping(source = "dto.categories", target = "categories")
    @Mapping(target = "attendees", ignore = true)
    @Mapping(target = "followers", ignore = true)
    @Mapping(source = "dto.url", target = "url")
    Event toUpdateEntity(EventDto dto, Event entity);

    @Override
    @Mapping(target = "attendees", ignore = true)
    @Mapping(target = "followers", ignore = true)
    Event toEntity(EventDto dto);
}
