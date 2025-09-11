package com.deilycodework.dream_shop.service.cart;

import com.deilycodework.dream_shop.model.Cart;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCartById(Long id);
    void clearCartById(Long id);
    BigDecimal getCartTotalPrice(Long id);
}
