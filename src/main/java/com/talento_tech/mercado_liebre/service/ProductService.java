package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.ProductCreateRequestDTO;
import com.talento_tech.mercado_liebre.dto.ProductDTO;
import com.talento_tech.mercado_liebre.dto.ProductUpdateDTO;
import com.talento_tech.mercado_liebre.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> listProducts();
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO createProduct(ProductCreateRequestDTO product);
    ProductDTO updateProduct(Long id, ProductUpdateDTO product);
    void deleteProductById(Long id);
    List<Product> findProductsByCriteria(
            String sectionName,
            String categoryName,
            String brandName,
            String productName,
            Double minPrice,
            Double maxPrice
    );
}
