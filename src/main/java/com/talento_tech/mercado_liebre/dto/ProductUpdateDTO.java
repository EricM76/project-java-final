package com.talento_tech.mercado_liebre.dto;

import com.talento_tech.mercado_liebre.model.Brand;
import com.talento_tech.mercado_liebre.model.Category;
import com.talento_tech.mercado_liebre.model.Section;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
public class ProductUpdateDTO {
    private String name;
    private Double price;
    private Integer discount;
    private URL image;

    @Lob
    private String description;
    private Integer stock;
    private Long categoryId;
    private Long brandId;
    private Category category;
    private Brand brand;
    private Section section;

    // Getters y Setters
}