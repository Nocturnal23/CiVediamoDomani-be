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

    private String username;

    private String password;

    private String email;

    private String firstName;

    private String lastName;

    private Long appRole;

    private List<EventDto> organisedEvents;

    private List<EventDto> favorites;

    private List<EventDto> attendes;

    private List<CategoryDto> categories;
}