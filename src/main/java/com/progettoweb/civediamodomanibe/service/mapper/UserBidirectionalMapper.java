package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.core.templates.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {EventBidirectionalMapper.class, CategoryBidirectionalMapper.class})
public interface UserBidirectionalMapper extends BidirectionalMapper<UserDto, UserAccount> {

    @Override
    @Mapping(target = "organisedEvents", expression = "java(entity.getOrganisedEvents() == null ? 0 : entity.getOrganisedEvents().size())")
    @Mapping(target = "favorites", expression = "java(entity.getFavorites() == null ? 0 : entity.getFavorites().size())")
    @Mapping(target = "attending", expression = "java(entity.getAttending() == null ? 0 : entity.getAttending().size())")
    UserDto toDto(UserAccount entity);

    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(source = "entity.deleted", target = "deleted")
    @Mapping(source = "entity.state", target = "state")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.appRole", target = "appRole")
    @Mapping(target = "organisedEvents", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "attending", ignore = true)
    @Mapping(source = "dto.categories", target = "categories")
    @Mapping(source = "dto.url", target = "url")
    UserAccount toUpdateEntity(UserDto dto, UserAccount entity);

    @Override
    @Mapping(target = "organisedEvents", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "attending", ignore = true)
    UserAccount toEntity(UserDto dto);
}
