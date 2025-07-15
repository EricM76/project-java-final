package com.talento_tech.mercado_liebre.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class UserUpdateDTO {
    private Long id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private URL image;
    private String token;
    private Boolean validated;
    private Boolean locked;
    private Long rolId;
}
