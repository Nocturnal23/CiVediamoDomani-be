package com.progettoweb.civediamodomanibe.service.mapper;

import com.progettoweb.civediamodomanibe.commons.BidirectionalMapper;
import com.progettoweb.civediamodomanibe.dto.CategoryDto;
import com.progettoweb.civediamodomanibe.entity.Category;
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
