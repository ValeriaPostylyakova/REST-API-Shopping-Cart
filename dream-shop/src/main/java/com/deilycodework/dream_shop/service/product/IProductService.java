package com.deilycodework.dream_shop.service.product;

import com.deilycodework.dream_shop.model.Product;
import com.deilycodework.dream_shop.request.AddProductRequest;
import com.deilycodework.dream_shop.request.ProductUpdateRequest;

import java.util.List;

public interface IProductService {
    Product addProduct(AddProductRequest productRequest);
    Product getProductById(Long id);
    List<Product> getAllProducts();
    List<Product> getProductByCategory(String category);
    List<Product> getProductsByBrand(String brand);
    List<Product> getProductsByCategoryAndBrand(String category, String brand);
    List<Product> getProductByName(String name);
    List<Product> getProductsByBrandAndName(String category, String name);
    Long countProductsByBrandAndName(String brand, String name);
    void deleteProductById(Long id);
    Product updateProduct(ProductUpdateRequest product, Long productId);


}
