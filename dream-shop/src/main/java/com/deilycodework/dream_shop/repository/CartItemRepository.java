package com.deilycodework.dream_shop.repository;

import com.deilycodework.dream_shop.model.CartItem;
import com.deilycodework.dream_shop.service.cartItem.CartItemService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
}
