package com.talento_tech.mercado_liebre.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class BrandDTO {
    private Long id;
    private String name;
    private URL image;

    public BrandDTO() {}

    public BrandDTO(Long id, String name, URL image){
        this.id = id;
        this.name = name;
        this.image = image;
    }}
