package com.talento_tech.mercado_liebre.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class CategoryDTO {
    private Long id;
    private String name;
    private URL image;

    public CategoryDTO(){}

    public  CategoryDTO(Long id, String name, URL image){
        this.id = id;
        this.name = name;
        this.image = image;
    }
}
