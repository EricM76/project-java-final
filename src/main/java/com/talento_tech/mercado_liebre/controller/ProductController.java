package com.talento_tech.mercado_liebre.controller;

import com.talento_tech.mercado_liebre.dto.ProductCreateRequestDTO;
import com.talento_tech.mercado_liebre.dto.ProductDTO;
import com.talento_tech.mercado_liebre.dto.ProductUpdateDTO;
import com.talento_tech.mercado_liebre.model.Product;
import com.talento_tech.mercado_liebre.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> list() {
        return productService.listProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
        return productService.getProductById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ProductDTO create(@RequestBody ProductCreateRequestDTO product) {
        return productService.createProduct(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductUpdateDTO product){
        if(productService.getProductById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        if(productService.getProductById(id).isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filter") // Un endpoint más genérico para filtros
    public ResponseEntity<List<Product>> filterProducts(
            @RequestParam(required = false) String section,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        List<Product> products = productService.findProductsByCriteria(section,category, brand, name, minPrice, maxPrice);
        return ResponseEntity.ok(products);
    }

}
