package com.talento_tech.mercado_liebre.service;

import com.talento_tech.mercado_liebre.dto.CategoryDTO_WithProducts;
import com.talento_tech.mercado_liebre.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> listCategories();
    Optional<CategoryDTO_WithProducts> getCategoryById(Long id);
    Category createCategory(Category category);
    Category updateCategory(Long id, Category category);
    void deleteCategoryById(Long id);
}
