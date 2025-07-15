package com.talento_tech.mercado_liebre.repository;

import com.talento_tech.mercado_liebre.model.Product;
import com.talento_tech.mercado_liebre.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
