package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.*;
import com.talento_tech.mercado_liebre.model.Category;
import com.talento_tech.mercado_liebre.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    private CategoryDTO_WithProducts mapToCategoryDTOWithProducts(Category category) {
        List<ProductDTO> productDTOs = category.getProducts().stream()
                .map(product -> new ProductDTO(
                        product.getId(),
                        product.getName(),
                        product.getPrice(),
                        product.getDiscount(),
                        product.getImage(),
                        product.getDescription(),
                        product.getStock(),
                        new CategoryDTO(product.getCategory().getId(), product.getCategory().getName(), product.getCategory().getImage()),
                        new BrandDTO(product.getBrand().getId(), product.getBrand().getName(), product.getBrand().getImage()),
                        new SectionDTO(product.getSection().getId(), product.getSection().getName())
                ))
                .collect(Collectors.toList());

        return new CategoryDTO_WithProducts(
                category.getId(),
                category.getName(),
                category.getImage(),
                productDTOs
        );
    }

    @Override
    public List<Category> listCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<CategoryDTO_WithProducts> getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .map(this::mapToCategoryDTOWithProducts);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Long id, Category category) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.deleteById(id);
    }
}
