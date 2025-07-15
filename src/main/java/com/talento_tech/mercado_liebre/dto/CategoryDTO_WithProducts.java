package com.talento_tech.mercado_liebre.dto;

import com.talento_tech.mercado_liebre.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.List;

@Getter
@Setter
public class CategoryDTO_WithProducts {
    private Long id;
    private String name;
    private URL image;
    private List<ProductDTO> products;

    public CategoryDTO_WithProducts(){}

    public  CategoryDTO_WithProducts(Long id, String name, URL image, List<ProductDTO> products){
        this.id = id;
        this.name = name;
        this.image = image;
        this.products = products;
    }
}
