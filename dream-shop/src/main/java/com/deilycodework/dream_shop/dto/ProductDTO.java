package com.deilycodework.dream_shop.dto;


import com.deilycodework.dream_shop.model.Product;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
        private Long id;
        private String name;
        private String brand;
        private String description;
        private BigDecimal price;
        private int quantity;
        private CategoryDTO category;

        public ProductDTO(Product product) {
            this.id = product.getId();
            this.name = product.getName();
            this.brand = product.getBrand();
            this.description = product.getDescription();
            this.price = product.getPrice();
            this.quantity = product.getQuantity();
            this.category = new CategoryDTO(product.getCategory());
        }
    }

