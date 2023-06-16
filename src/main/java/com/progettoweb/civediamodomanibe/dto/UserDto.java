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

    private int organisedEvents;

    private int favorites;

    private int attending;

    private List<CategoryDto> categories;
}