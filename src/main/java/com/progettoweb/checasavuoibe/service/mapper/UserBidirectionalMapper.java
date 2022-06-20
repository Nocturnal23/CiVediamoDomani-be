package com.progettoweb.checasavuoibe.service.mapper;

import com.progettoweb.checasavuoibe.commons.BidirectionalMapper;
import com.progettoweb.checasavuoibe.dto.UserDto;
import com.progettoweb.checasavuoibe.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserBidirectionalMapper extends BidirectionalMapper<UserDto, User> {
}
