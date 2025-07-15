package com.talento_tech.mercado_liebre.dto;

import com.talento_tech.mercado_liebre.model.Rol;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private URL image;
    private Rol rol;

    public UserDTO(){}

    public UserDTO(
            Long id,
            String name,
            String surname,
            String email,
            URL image,
            Rol rol
    ) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.image = image;
        this.rol = rol;
    }
}
