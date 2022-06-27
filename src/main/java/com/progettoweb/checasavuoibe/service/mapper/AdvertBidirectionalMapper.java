package com.progettoweb.checasavuoibe.service.mapper;

import com.progettoweb.checasavuoibe.commons.BidirectionalMapper;
import com.progettoweb.checasavuoibe.dto.AdvertDto;
import com.progettoweb.checasavuoibe.entity.Advert;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {UserBidirectionalMapper.class, ReviewBidirectionalMapper.class, CategoryBidirectionalMapper.class})
public interface AdvertBidirectionalMapper extends BidirectionalMapper<AdvertDto, Advert> {
    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(source = "dto.advertiser", target = "advertiser")
    @Mapping(source = "dto.address", target = "address")
    @Mapping(source = "dto.city", target = "city")
    @Mapping(source = "dto.country", target = "country")
    @Mapping(source = "dto.constructionYear", target = "constructionYear")
    @Mapping(source = "dto.placeCondition", target = "placeCondition")
    @Mapping(source = "dto.floor", target = "floor")
    @Mapping(source = "dto.squareMeters", target = "squareMeters")
    @Mapping(source = "dto.noLocals", target = "noLocals")
    @Mapping(source = "dto.noBathrooms", target = "noBathrooms")
    @Mapping(source = "dto.title", target = "title")
    @Mapping(source = "dto.description", target = "description")
    @Mapping(source = "dto.price", target = "price")
    @Mapping(source = "dto.discountPrice", target = "discountPrice")
    @Mapping(source = "dto.advType", target = "advType")
    @Mapping(source = "dto.sellerType", target = "sellerType")
    @Mapping(source = "dto.parking", target = "parking")
    @Mapping(source = "dto.media", target = "media")
    @Mapping(source = "dto.review", target = "review")
    @Mapping(source = "dto.heatingType", target = "heatingType")
    @Mapping(source = "dto.energeticClass", target = "energeticClass")
    @Mapping(source = "dto.details", target = "details")
    @Mapping(source = "dto.category", target = "category")

    Advert toUpdateEntity(AdvertDto dto, Advert entity);
}
