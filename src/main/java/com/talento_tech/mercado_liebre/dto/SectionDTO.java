package com.talento_tech.mercado_liebre.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class SectionDTO {
    private Long id;
    private String name;

    public SectionDTO() {}

    public SectionDTO(
            Long id,
            String name
    ){
        this.id = id;
        this.name = name;
    }}
