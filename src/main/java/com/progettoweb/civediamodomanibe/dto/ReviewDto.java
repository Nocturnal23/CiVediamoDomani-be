package com.progettoweb.civediamodomanibe.dto;

import com.progettoweb.civediamodomanibe.commons.BaseDto;
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