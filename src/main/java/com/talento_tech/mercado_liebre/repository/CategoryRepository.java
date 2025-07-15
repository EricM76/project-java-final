package com.talento_tech.mercado_liebre.repository;

import com.talento_tech.mercado_liebre.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
