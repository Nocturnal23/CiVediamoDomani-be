package com.progettoweb.checasavuoibe.dto;

import com.progettoweb.checasavuoibe.commons.BaseDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends BaseDto {

    private String name;

    private List<AdvertDto> adverts;
}