package com.talento_tech.mercado_liebre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "sections")
@Getter
@Setter
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Section(){}

    public Section(
            Long id,
            String name
    ){
        this.id = id;
        this.name = name;
    }
    public void addProduct(Product product) {
        products.add(product);
        product.setSection(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setSection(null);
    }
}
