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
    @Mapping(source = "entity.id", target = "id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(source = "dto.username", target = "username")
    @Mapping(source = "dto.password", target = "password")
    @Mapping(source = "dto.email", target = "email")
    @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.appRole", target = "appRole")
    @Mapping(source = "dto.organisedEvents", target = "organisedEvents")
    @Mapping(source = "dto.favorites", target = "favorites")
    @Mapping(source = "dto.attendes", target = "attendes")
    @Mapping(source = "dto.categories", target = "categories")
    UserAccount toUpdateEntity(UserDto dto, UserAccount entity);
}
