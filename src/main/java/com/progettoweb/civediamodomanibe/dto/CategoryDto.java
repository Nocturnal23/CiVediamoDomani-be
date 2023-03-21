package com.progettoweb.civediamodomanibe.dto;

import com.progettoweb.civediamodomanibe.core.templates.BaseDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto extends BaseDto {

    private String name;

    private CategoryDto father;
}