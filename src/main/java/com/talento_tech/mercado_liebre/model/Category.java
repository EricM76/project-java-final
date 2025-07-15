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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "categories")
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private URL image;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Product> products = new ArrayList<>();

    public Category(){}

    public  Category(Long id, String name, URL image){
        this.id = id;
        this.name = name;
        this.image = image;
    }

   public void addProduct(Product product) {
        products.add(product);
        product.setCategory(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setCategory(null);
    }
}

/*
Explicación de @OneToMany en Category:
--------------------------------------
@OneToMany: Indica que una Category puede tener muchos Products.

mappedBy = "category": Este es el atributo más importante. Le dice a JPA que la relación es bidireccional y que el
campo category en la clase Product es el propietario de la relación. Esto significa que la clave foránea (category_id)
estará en la tabla products.

cascade = CascadeType.ALL: Cuando realizas una operación (persistencia, actualización, eliminación) en una Category,
esa operación también se propaga a sus Products asociados. Esto es útil para mantener la consistencia de los datos.
Por ejemplo, si eliminas una categoría, todos sus productos asociados también se eliminarán.

orphanRemoval = true: Si un Product se elimina de la lista products de una Category, ese Product también será eliminado
de la base de datos.

private List<Product> products = new ArrayList<>();: Es una buena práctica inicializar la lista para evitar
NullPointerExceptions.

addProduct(Product product) y removeProduct(Product product): Métodos de utilidad para manejar la relación
bidireccional. Es crucial que cuando añades o quitas un producto de la lista de la categoría, también establezcas o
quites la categoría del producto, respectivamente. Esto es lo que se conoce como mantener la "bidireccionalidad" de la
relación.
*/