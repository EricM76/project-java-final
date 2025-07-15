package com.talento_tech.mercado_liebre.specification;

import com.talento_tech.mercado_liebre.model.Brand;
import com.talento_tech.mercado_liebre.model.Category;
import com.talento_tech.mercado_liebre.model.Product;
import com.talento_tech.mercado_liebre.model.Section;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> hasSectionName(String sectionName){
        return ((root, query, criteriaBuilder) -> {
            if(sectionName == null || sectionName.trim().isEmpty()){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            Join<Product, Section> sectionJoin = root.join("section", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(sectionJoin.get("name")),"%" + sectionName.toLowerCase() + "%");
        });
    }

    public static Specification<Product> hasCategoryName(String categoryName){
        return ((root, query, criteriaBuilder) -> {
            if(categoryName == null || categoryName.trim().isEmpty()){
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            Join<Product, Category> categoryJoin = root.join("category", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(categoryJoin.get("name")),"%" + categoryName.toLowerCase() + "%");
        });
    }

    public static Specification<Product> hasBrandName(String brandName) {
        return (root, query, criteriaBuilder) -> {
            if (brandName == null || brandName.trim().isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true)); // Siempre verdadero si no hay marca
            }
            // Realiza un JOIN a la tabla Brand y filtra por el nombre
            Join<Product, Brand> brandJoin = root.join("brand", JoinType.INNER);
            return criteriaBuilder.like(criteriaBuilder.lower(brandJoin.get("name")), "%" + brandName.toLowerCase() + "%");
        };
    }

    public static Specification<Product> nameContains(String name) {
        return (root, query, criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Product> hasMinPrice(Double minPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
        };
    }

    public static Specification<Product> hasMaxPrice(Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (maxPrice == null) {
                return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
            }
            return criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
        };
    }
}

/*
Explicación de Specification:
- Cada metodo estático devuelve un Specification<Product>.
- El lambda (root, query, criteriaBuilder) -> { ... } define el criterio de la consulta.
    - root: Representa la entidad raíz (Product en este caso).
    - query: Representa la consulta JPA.
    - criteriaBuilder: Un builder para construir las expresiones de criterio (ej. like, equalTo, greaterThan).
- Join<Product, Category> categoryJoin = root.join("category", JoinType.INNER);: Esto es crucial. Como category y brand
    son relaciones ManyToOne, necesitamos un JOIN explícito para acceder a sus propiedades (name).
- criteriaBuilder.like(...): Para búsquedas tipo "contiene" (como si usaras ContainingIgnoreCase en el repositorio).
- criteriaBuilder.isTrue(criteriaBuilder.literal(true)): Si el parámetro de entrada es nulo o vacío, esta expresión
simplemente devuelve "verdadero", lo que significa que este criterio no aplicará ningún filtro y permitirá que el
producto pase.
*/
