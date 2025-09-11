package com.deilycodework.dream_shop.service.cartItem;

import com.deilycodework.dream_shop.exception.CartNotFoundException;
import com.deilycodework.dream_shop.model.Cart;
import com.deilycodework.dream_shop.model.CartItem;
import com.deilycodework.dream_shop.model.Product;
import com.deilycodework.dream_shop.repository.CartItemRepository;
import com.deilycodework.dream_shop.repository.CartRepository;
import com.deilycodework.dream_shop.service.cart.ICartService;
import com.deilycodework.dream_shop.service.product.IProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service

public class CartItemService implements ICartItemService {
    private final CartItemRepository cartItemRepository;
    private final IProductService productService;
    private final ICartService cartService;
    private final CartRepository cartRepository;
    @Transactional
    @Override
    public void addItemToCart(Long cartId, Long productId, Integer quantity) {
        Cart cart = cartService.getCartById(cartId);
        Product product = productService.getProductById(productId);
        CartItem cartItem = cart.getCartItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(new CartItem());

        if (cartItem.getId() == null) {
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);
            cartItem.setUnitPrice(product.getPrice());
        }

        else {
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }

        cartItem.setTotalPrice();
        cart.addItem(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

    }

    @Override
    public void removeItemFromCart(Long cartId, Long productId) {

    }

    @Override
    public void updateCartItemQuantity(Long cartId, Long productId, Integer quantity) {

    }
}
