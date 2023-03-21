package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.core.templates.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.EventDto;
import com.progettoweb.civediamodomanibe.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserBidirectionalMapper.class, CategoryBidirectionalMapper.class})
public interface EventBidirectionalMapper extends BidirectionalMapper<EventDto, Event> {
    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(source = "dto.organiser", target = "organiser")
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.place", target = "place")
    @Mapping(source = "dto.coordinates", target = "coordinates")
    @Mapping(source = "dto.datetime", target = "datetime")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.price", target = "price")
    @Mapping(source = "dto.categories", target = "categories")
    @Mapping(source = "dto.attendees", target = "attendees")
    @Mapping(source = "dto.followers", target = "followers")
    Event toUpdateEntity(EventDto dto, Event entity);
}
