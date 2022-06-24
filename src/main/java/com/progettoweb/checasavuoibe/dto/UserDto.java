package com.progettoweb.checasavuoibe.dto;

import com.progettoweb.checasavuoibe.commons.BaseDto;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto extends BaseDto {

    private String firstName;

    private String lastName;
}