package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.commons.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.ReviewDto;
import com.progettoweb.civediamodomanibe.entity.Review;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserBidirectionalMapper.class, AdvertBidirectionalMapper.class})
public interface ReviewBidirectionalMapper extends BidirectionalMapper<ReviewDto, Review> {
    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(source = "dto.sender", target = "sender")
    @Mapping(source = "dto.recipient", target = "recipient")
    @Mapping(source = "dto.rating", target = "rating")
    @Mapping(source = "dto.description", target = "description")
    Review toUpdateEntity(ReviewDto dto, Review entity);
}
