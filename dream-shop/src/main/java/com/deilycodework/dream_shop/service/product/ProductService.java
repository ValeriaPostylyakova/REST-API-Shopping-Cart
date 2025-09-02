package com.deilycodework.dream_shop.service.product;

import com.deilycodework.dream_shop.exception.ProductNotFoundException;
import com.deilycodework.dream_shop.model.Category;
import com.deilycodework.dream_shop.model.Product;
import com.deilycodework.dream_shop.repository.CategoryRepository;
import com.deilycodework.dream_shop.repository.ProductRepository;
import com.deilycodework.dream_shop.request.AddProductRequest;
import com.deilycodework.dream_shop.request.ProductUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements IProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product addProduct(AddProductRequest productRequest) {
        Category category = Optional.ofNullable(categoryRepository.findByName(productRequest.getCategory().getName())).orElseGet(() -> {
            Category newCategory = new Category(productRequest.getCategory().getName());
            return categoryRepository.save(newCategory);
        });

        productRequest.setCategory(category);
        return productRepository.save(createProduct(productRequest, category));
    }

    private Product createProduct(AddProductRequest productRequest, Category category) {
        return new Product(
                productRequest.getName(),
                productRequest.getBrand(),
                productRequest.getDescription(),
                productRequest.getPrice(),
                productRequest.getQuantity(),
                category
        );
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
        return productRepository.findByCategoryName(category);
    }

    @Override
    public List<Product> getProductsByBrand(String brand) {
        return productRepository.findByBrand(brand);
    }

    @Override
    public List<Product> getProductsByCategoryAndBrand(String category, String brand) {
        return productRepository.findByCategoryAndBrand(category, brand);
    }

    @Override
    public List<Product> getProductByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getProductsByBrandAndName(String brand, String name) {
        return productRepository.findByBrandAndName(brand, name);
    }

    @Override
    public Long countProductsByBrandAndName(String brand, String name) {
        return productRepository.countByBrandAndName(brand, name);
    }

    @Override
    public void deleteProductById(Long id) {
    productRepository.findById(id).ifPresentOrElse(productRepository::delete, () -> {throw  new ProductNotFoundException("Product not found!");});
    }

    @Override
    public Product updateProduct(ProductUpdateRequest request, Long productId) {
  return productRepository.findById(productId)
          .map(existingProduct -> updateExistingProduct(existingProduct, request))
          .map(productRepository :: save)
          .orElseThrow(() -> new ProductNotFoundException("Product not found!"));
    }

    private Product updateExistingProduct(Product existingProduct, ProductUpdateRequest request) {
        existingProduct.setName(request.getName());
        existingProduct.setBrand(request.getBrand());
        existingProduct.setDescription(request.getDescription());
        existingProduct.setPrice(request.getPrice());
        existingProduct.setQuantity(request.getQuantity());

        Category category = categoryRepository.findByName(request.getCategory().getName());
        existingProduct.setCategory(category);
        return existingProduct;
    }
}
