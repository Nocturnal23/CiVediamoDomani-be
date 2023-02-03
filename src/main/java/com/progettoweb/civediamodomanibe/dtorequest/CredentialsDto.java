package com.progettoweb.civediamodomanibe.dtorequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CredentialsDto {

    private String email;
    private char[] password;

}
