package com.deilycodework.dream_shop.service.category;

import com.deilycodework.dream_shop.exception.CategoryNotFoundException;
import com.deilycodework.dream_shop.model.Category;
import com.deilycodework.dream_shop.repository.CategoryRepository;
import com.deilycodework.dream_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService implements ICategoryService {
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() ->  new CategoryNotFoundException("Category not found!"));
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getCategoryAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category addCategory(Category category) {
        return Optional.of(category).filter(c -> !categoryRepository.existsByName(c.getName()))
                .map(categoryRepository :: save)
                .orElseThrow(() -> new CategoryNotFoundException(category.getName() + "already exists"));
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        return Optional.ofNullable(getCategoryById(id)).map(prevCategory -> {
            prevCategory.setName(category.getName());
            return categoryRepository.save(prevCategory);
        }).orElseThrow(() -> new CategoryNotFoundException("Category not found!"));
    }

    @Override
    public void deleteCategoryById(Long id) {
        categoryRepository.findById(id).ifPresentOrElse(categoryRepository::delete,
                () -> {throw new CategoryNotFoundException("Category not found!");});
    }
}
