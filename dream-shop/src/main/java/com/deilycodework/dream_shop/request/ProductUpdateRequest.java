package com.deilycodework.dream_shop.request;

import com.deilycodework.dream_shop.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductUpdateRequest {
    private Long id;
    private String name;
    private String brand;
    private String description;
    private BigDecimal price;
    private int quantity;
    private Category category;
}
