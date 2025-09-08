package com.deilycodework.dream_shop.dto;

import com.deilycodework.dream_shop.model.Category;
import lombok.Data;

@Data
public class CategoryDTO {
    private Long id;
    private String name;

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
    }
}
