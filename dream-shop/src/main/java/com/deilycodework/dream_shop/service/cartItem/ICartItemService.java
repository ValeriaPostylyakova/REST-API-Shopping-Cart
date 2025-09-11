package com.deilycodework.dream_shop.service.cartItem;

import com.deilycodework.dream_shop.model.CartItem;

public interface ICartItemService {
    void addItemToCart(Long cartId, Long productId, Integer quantity);
    void removeItemFromCart(Long cartId, Long productId);
    void updateCartItemQuantity(Long cartId, Long productId, Integer quantity);
}
