package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.core.templates.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.UserAccount;
import org.mapstruct.*;

import java.util.Collections;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {EventBidirectionalMapper.class, CategoryBidirectionalMapper.class},
        imports = {Collections.class, Collectors.class})
public interface UserBidirectionalMapper extends BidirectionalMapper<UserDto, UserAccount> {

    @Override
    @Mapping(target = "organisedEvents", expression = "java(entity.getAttending() == null ? Collections.singletonList(\"\") : entity.getOrganisedEvents().stream().map(event -> event.getUrl()).collect(Collectors.toList()))")
    @Mapping(target = "attending", expression = "java(entity.getAttending() == null ? Collections.singletonList(\"\") : entity.getAttending().stream().map(event -> event.getUrl()).collect(Collectors.toList()))")
    @Mapping(target = "favorites", expression = "java(entity.getFavorites() == null ? Collections.singletonList(\"\") : entity.getFavorites().stream().map(event -> event.getUrl()).collect(Collectors.toList()))")
    UserDto  toDto(UserAccount entity);

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
    @Mapping(source = "dto.url", target = "url")
    @Mapping(source = "dto.searchLocation", target = "searchLocation")
    @Mapping(source = "dto.searchLatitude", target = "searchLatitude")
    @Mapping(source = "dto.searchLongitude", target = "searchLongitude")
    UserAccount toUpdateEntity(UserDto dto, UserAccount entity);

    @Override
    @Mapping(target = "organisedEvents", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    @Mapping(target = "attending", ignore = true)
    UserAccount toEntity(UserDto dto);
}
