package com.deilycodework.dream_shop.service.product;

import com.deilycodework.dream_shop.exception.ProductNotFoundException;
import com.deilycodework.dream_shop.model.Product;
import com.deilycodework.dream_shop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private ProductRepository productRepository;
    @Override
    public Product addProduct(Product product) {
        return null;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> getProductByCategory(String category) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return List.of();
    }

    @Override
    public List<Product> getProductByName(String name) {
        return List.of();
    }

    @Override
    public List<Product> getProductsByBrandAndName(String category, String name) {
        return List.of();
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return 0;
    }

    @Override
    public void deleteProductById(Long id) {
    productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {throw  new ProductNotFoundException("Product not found!");});
    }

    @Override
    public void updateProduct(Product product, Long productId) {

    }
}
