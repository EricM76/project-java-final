package com.talento_tech.mercado_liebre.dto;

import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
    private Integer discount;
    private URL image;
    @Lob
    private String description;
    private Integer stock;
    private CategoryDTO category;
    private BrandDTO brand;
    private SectionDTO section;

    public ProductDTO(){}

    public ProductDTO(
            Long id,
            String name,
            Double price,
            Integer discount,
            URL image,
            String description,
            Integer stock,
            CategoryDTO category,
            BrandDTO brand,
            SectionDTO section
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.description = description;
        this.stock = stock;
        this.category = category;
        this.brand = brand;
        this.section = section;
    }
}
