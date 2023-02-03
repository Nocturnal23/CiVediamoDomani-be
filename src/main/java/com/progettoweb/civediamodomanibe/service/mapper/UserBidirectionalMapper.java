package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.commons.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.UserDto;
import com.progettoweb.civediamodomanibe.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {AdvertBidirectionalMapper.class, ReviewBidirectionalMapper.class})
public interface UserBidirectionalMapper extends BidirectionalMapper<UserDto, User> {

    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(source = "dto.firstName", target = "firstName")
    @Mapping(source = "dto.lastName", target = "lastName")
    @Mapping(source = "dto.email", target = "email")
    @Mapping(source = "dto.phoneNum", target = "phoneNum")
    @Mapping(source = "dto.birthday", target = "birthday")
    @Mapping(source = "dto.city", target = "city")
    @Mapping(source = "dto.country", target = "country")
    @Mapping(source = "dto.appRole", target = "appRole")
    @Mapping(source = "dto.postedAds", target = "postedAds")
    @Mapping(source = "dto.savedAds", target = "savedAds")
    @Mapping(source = "dto.postedReviews", target = "postedReviews")
    User toUpdateEntity(UserDto dto, User entity);
}
