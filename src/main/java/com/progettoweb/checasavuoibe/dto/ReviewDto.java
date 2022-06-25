package com.progettoweb.checasavuoibe.dto;

import com.progettoweb.checasavuoibe.commons.BaseDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto extends BaseDto {

    private UserDto sender;

    private AdvertDto recipient;

    private Long rating;

    private String description;
}