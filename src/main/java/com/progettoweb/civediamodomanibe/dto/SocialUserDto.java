package com.progettoweb.civediamodomanibe.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocialUserDto {
    private String provider;
    private String id;
    private String email;
    private String name;
    private String photoUrl;
    private String firstName;
    private String lastName;
    private String authToken;
    private String idToken;
    private String authorizationCode;
    private String response;
}
