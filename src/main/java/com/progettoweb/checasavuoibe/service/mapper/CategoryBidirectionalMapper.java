package com.progettoweb.checasavuoibe.service.mapper;

import com.progettoweb.checasavuoibe.commons.BidirectionalMapper;
import com.progettoweb.checasavuoibe.dto.CategoryDto;
import com.progettoweb.checasavuoibe.entity.Category;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {AdvertBidirectionalMapper.class})
public interface CategoryBidirectionalMapper extends BidirectionalMapper<CategoryDto, Category> {
    @Override
    @Mapping(source = "entity.id", target = "id")
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "modifiedDate", ignore = true)
    @Mapping(source = "dto.name", target = "name")
    @Mapping(source = "dto.adverts", target = "adverts")

    Category toUpdateEntity(CategoryDto dto, Category entity);
}
