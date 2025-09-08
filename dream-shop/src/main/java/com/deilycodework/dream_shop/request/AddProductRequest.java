package com.deilycodework.dream_shop.request;

import com.deilycodework.dream_shop.model.Category;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int quantity;
    private String category;
}
