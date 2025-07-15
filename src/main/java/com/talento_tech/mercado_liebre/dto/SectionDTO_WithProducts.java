package com.talento_tech.mercado_liebre.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class SectionDTO_WithProducts {
    private Long id;
    private String name;
    private List<ProductDTO> products;

    public SectionDTO_WithProducts(){}

    public SectionDTO_WithProducts(
            Long id,
            String name,
            List<ProductDTO> products
    ){
        this.id = id;
        this.name = name;
        this.products = products;
    }
}
