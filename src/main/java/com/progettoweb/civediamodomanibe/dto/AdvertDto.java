package com.progettoweb.civediamodomanibe.dto;

import com.progettoweb.civediamodomanibe.commons.BaseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdvertDto extends BaseDto {

    private UserDto advertiser;

    private String address;

    private String city;

    private String country;

    private LocalDate constructionYear;

    private String placeCondition;

    private int floor;

    private Double squareMeters;

    private int noLocals;

    private int noBathrooms;

    private String title;

    private String description;

    private Double price;

    private Double discountPrice;

    private Long advType;

    private Long sellerType;

    private Long parking;

    private String media;

    private List<ReviewDto> review;

    private Long heatingType;

    private Long energeticClass;

    private String details;

    private CategoryDto category;
}