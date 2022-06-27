package com.progettoweb.checasavuoibe.dto;

import com.progettoweb.checasavuoibe.commons.BaseDto;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNum;

    private LocalDate birthday;

    private String city;

    private String country;

    private Long appRole;

    private List<AdvertDto> postedAds;

    private List<AdvertDto> savedAds;

    private List<ReviewDto> postedReviews;
}