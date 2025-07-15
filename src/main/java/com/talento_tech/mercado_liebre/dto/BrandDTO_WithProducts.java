package com.talento_tech.mercado_liebre.dto;

import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Setter
public class BrandDTO_WithProducts {
    private Long id;
    private String name;
    private URL image;
    private List<ProductDTO> products;

    public BrandDTO_WithProducts(){}

    public BrandDTO_WithProducts(Long id, String name, URL image, List<ProductDTO> products){
        this.id = id;
        this.name = name;
        this.image = image;
        this.products = products;
    }
}
