package com.talento_tech.mercado_liebre.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer discount;
    private URL image;

    @Lob
    private String description;
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_id")
    private Section section;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    public Product() {}

    public Product(
            Long id,
            String name,
            Double price,
            Integer discount,
            URL image,
            String description,
            Integer stock
    ) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
        this.image = image;
        this.description = description;
        this.stock = stock;
    }
}
/*
Explicación de @ManyToOne en Product:

@ManyToOne: Indica que muchos Products pueden pertenecer a una Category.

fetch = FetchType.LAZY: Esta es una optimización importante. Significa que la Category asociada solo se cargará de la
base de datos cuando se acceda a ella (por ejemplo, product.getCategory().getName()). Si usaras EAGER, la categoría se
cargaría automáticamente cada vez que se carga un Product, lo que podría llevar a problemas de rendimiento
(especialmente N+1 queries).

@JoinColumn(name = "category_id"): Especifica la columna de la clave foránea en la tabla products que apunta a la tabla
categories. Si no especificas name, JPA generará un nombre por defecto (ej. category_id), pero es una buena práctica
ser explícito.
*/
