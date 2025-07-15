package com.talento_tech.mercado_liebre.dto;

import com.talento_tech.mercado_liebre.model.Brand;
import com.talento_tech.mercado_liebre.model.Category;
import com.talento_tech.mercado_liebre.model.Section;
import lombok.Getter;

import java.net.URL;

@Getter
public class ProductCreateRequestDTO {
    private String name;
    private Double price;
    private Integer discount;
    private URL image;
    private String description;
    private Integer stock;
    private Category category;
    private Brand brand;
    private Section section;
}
