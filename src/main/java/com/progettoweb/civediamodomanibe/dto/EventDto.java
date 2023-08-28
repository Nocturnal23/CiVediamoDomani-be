package com.progettoweb.civediamodomanibe.dto;

import com.progettoweb.civediamodomanibe.core.templates.BaseDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto extends BaseDto {

    private UserDto organiser;

    private String title;

    private String place;

    private String coordinates;

    private LocalDateTime datetime;

    private String description;

    private Float price;

    private byte[] image;

    private List<CategoryDto> categories;

    private List<String> attendees;

    private List<String> followers;
}