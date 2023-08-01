package com.progettoweb.civediamodomanibe.dto;

import com.progettoweb.civediamodomanibe.core.templates.BaseDto;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String state;

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    private Long appRole;

    private List<String> organisedEvents;

    private List<String> favorites;

    private List<String> attending;

    private List<CategoryDto> categories;

    private String searchLocation;

    private String searchLatitude;

    private String searchLongitude;
}