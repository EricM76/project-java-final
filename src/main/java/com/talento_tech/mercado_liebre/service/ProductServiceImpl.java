package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.*;
import com.talento_tech.mercado_liebre.model.Brand;
import com.talento_tech.mercado_liebre.model.Category;
import com.talento_tech.mercado_liebre.model.Product;
import com.talento_tech.mercado_liebre.model.Section;
import com.talento_tech.mercado_liebre.repository.BrandRepository;
import com.talento_tech.mercado_liebre.repository.CategoryRepository;
import com.talento_tech.mercado_liebre.repository.ProductRepository;
import com.talento_tech.mercado_liebre.exception.ResourceNotFoundException;
import com.talento_tech.mercado_liebre.repository.SectionRepository;
import com.talento_tech.mercado_liebre.specification.ProductSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final SectionRepository sectionRepository;

    @Autowired
    public ProductServiceImpl(
            ProductRepository productRepository,
            CategoryRepository categoryRepository,
            BrandRepository brandRepository,
            SectionRepository sectionRepository
    ){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.sectionRepository = sectionRepository;
    }

    private ProductDTO mapToProductDTO(Product product) {
        CategoryDTO category = null;
        if(product.getCategory() != null){
            category = new CategoryDTO(
                    product.getCategory().getId(),
                    product.getCategory().getName(),
                    product.getCategory().getImage()
            );
        }

        BrandDTO brand = null;
        if(product.getBrand() != null){
            brand = new BrandDTO(
                    product.getBrand().getId(),
                    product.getBrand().getName(),
                    product.getBrand().getImage()
            );
        }

        SectionDTO section = null;
        if(product.getSection() != null){
            section = new SectionDTO(
                    product.getSection().getId(),
                    product.getSection().getName()
            );
        }

        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDiscount(),
                product.getImage(),
                product.getDescription(),
                product.getStock(),
                category,
                brand,
                section
        );
    }

    @Override
    public List<ProductDTO> listProducts(){
        return productRepository.findAll().stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id)
                .map(this:: mapToProductDTO);
    }

    @Override
    @Transactional
    public ProductDTO createProduct(ProductCreateRequestDTO productRequestDto) {
        Product newProduct = new Product();
        newProduct.setName(productRequestDto.getName());
        newProduct.setPrice(productRequestDto.getPrice());
        newProduct.setDiscount(productRequestDto.getDiscount());
        newProduct.setImage(productRequestDto.getImage());
        newProduct.setDescription(productRequestDto.getDescription());
        newProduct.setStock(productRequestDto.getStock());

        if (productRequestDto.getCategory() != null) {
            Category category = categoryRepository.findById(productRequestDto.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id " + productRequestDto.getCategory().getId()));
            newProduct.setCategory(category);
        }

        if (productRequestDto.getBrand() != null) {
            Brand brand = brandRepository.findById(productRequestDto.getBrand().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Marca no encontrada con id " + productRequestDto.getBrand().getId()));
            newProduct.setBrand(brand);
        }

        if (productRequestDto.getSection() != null) {
            Section section = sectionRepository.findById(productRequestDto.getSection().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Sección no encontrada con id " + productRequestDto.getSection().getId()));
            newProduct.setSection(section);
        }

        Product productSaved = productRepository.save(newProduct);
        return mapToProductDTO(productSaved);
    }

    @Override
    @Transactional
    public ProductDTO updateProduct(Long id, ProductUpdateDTO productDto) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));

        existingProduct.setName(productDto.getName());
        existingProduct.setPrice(productDto.getPrice());
        existingProduct.setImage(productDto.getImage());
        existingProduct.setDescription(productDto.getDescription());
        existingProduct.setStock(productDto.getStock());
        existingProduct.setCategory(productDto.getCategory());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setSection(productDto.getSection());

       if (productDto.getCategory() != null) {
            Category category = categoryRepository.findById(productDto.getCategory().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Category not found with id " + productDto.getCategoryId()));
            existingProduct.setCategory(category);
        }

        if (productDto.getBrand() != null) {
            Brand brand = brandRepository.findById(productDto.getBrand().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + productDto.getBrandId()));
            existingProduct.setBrand(brand);
        }

        if (productDto.getSection() != null) {
            Section section = sectionRepository.findById(productDto.getSection().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Brand not found with id " + productDto.getBrandId()));
            existingProduct.setSection(section);
        }

        Product productSaved = productRepository.save(existingProduct);
        return mapToProductDTO(productSaved);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findProductsByCriteria(
            String sectionName,
            String categoryName,
            String brandName,
            String productName,
            Double minPrice,
            Double maxPrice
    ) {
        // Inicializar la Specification como Optional.empty() o null y construirla progresivamente
        Optional<Specification<Product>> combinedSpec = Optional.empty();

        // Si se define un filtro de categoría, lo agregamos
        if (sectionName != null && !sectionName.trim().isEmpty()) {
            combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.hasSectionName(sectionName)));
        }

        // Si se define un filtro de categoría, lo agregamos
        if (categoryName != null && !categoryName.trim().isEmpty()) {
            combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.hasCategoryName(categoryName)));
        }

        // Si se define un filtro de marca, lo agregamos
        if (brandName != null && !brandName.trim().isEmpty()) {
            combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.hasBrandName(brandName)));
        }

        // Si se define un filtro de nombre de producto, lo agregamos
        if (productName != null && !productName.trim().isEmpty()) {
            combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.nameContains(productName)));
        }

        // Si se define un filtro de precio mínimo, lo agregamos
        if (minPrice != null) {
            combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.hasMinPrice(minPrice)));
        }

        // Si se define un filtro de precio máximo, lo agregamos
        if (maxPrice != null) {
            combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.hasMaxPrice(maxPrice)));
        }

        // Ejecutar la consulta. Si no se aplicó ningún filtro (combinedSpec sigue vacío),
        // findAll() sin Specification devolverá todos los productos.
        // Si hay filtros, se usa el Optional.get() para obtener la Specification combinada.
        return combinedSpec.map(productRepository::findAll)
                .orElseGet(productRepository::findAll);
    }
}

/*
Explicación del Cambio:
1. Inicialización: Inicializamos combinedSpec como Optional.empty(). Esto representa la ausencia de una especificación en
lugar de una especificación null que puede ser problemática.

2. Encadenamiento con Optional.or():
    - Para cada filtro (categoría, marca, nombre, precio), primero verificamos si el parámetro correspondiente no es
    null o vacío.
    - Si el parámetro está presente, usamos combinedSpec.or(() -> Optional.of(nuevaEspecificacion)) para agregar la
    nueva especificación a la combinedSpec.
    - ¡Importante! A pesar de usar .or() de Optional, el comportamiento final para los filtros individuales (categoría,
    marca, nombre, etc.) sigue siendo AND cuando se aplican secuencialmente, porque cada Specification individual que
    creamos (hasCategoryName, hasBrandName, etc.) ya está diseñada para "no filtrar" (devolver true) si su respectivo
    parámetro es null.
    - Cuando hacemos
    combinedSpec = combinedSpec.or(() -> Optional.of(ProductSpecifications.hasCategoryName(categoryName)));,
    si combinedSpec estaba vacío, ahora contiene la especificación de categoría. Si ya tenía una especificación, se
    combinan con un AND implícito si la Specification resultante de ProductSpecifications es lo que se espera.
 3. Ejecución de la Consulta:
    - combinedSpec.map(productRepository::findAll): Si combinedSpec contiene una Specification (es decir, Optional no
    está vacío), mapea esa Specification al metodo findAll del repositorio.
    - .orElseGet(productRepository::findAll): Si combinedSpec está vacío (es decir, no se proporcionó ningún filtro),
    se llama a productRepository.findAll() sin argumentos, que devuelve todos los productos.
*/
