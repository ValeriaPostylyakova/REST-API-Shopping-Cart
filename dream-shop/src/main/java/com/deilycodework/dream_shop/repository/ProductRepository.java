package com.deilycodework.dream_shop.repository;

import com.deilycodework.dream_shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
