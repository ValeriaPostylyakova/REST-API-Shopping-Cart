package com.deilycodework.dream_shop.service.cart;

import com.deilycodework.dream_shop.exception.CartNotFoundException;
import com.deilycodework.dream_shop.model.Cart;
import com.deilycodework.dream_shop.model.CartItem;
import com.deilycodework.dream_shop.repository.CartItemRepository;
import com.deilycodework.dream_shop.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    @Override
    public Cart getCartById(Long id) {
       return cartRepository.findById(id)
               .orElseThrow(() -> new CartNotFoundException("Cart not found!"));
    }

    @Override
    @Transactional
    public void clearCartById(Long id) {
        Cart cart = getCartById(id);
        cart.getCartItems().clear();
        cart.setTotalPrice(BigDecimal.ZERO);
        cartRepository.save(cart);
    }

    @Override
    @Transactional
    public BigDecimal getCartTotalPrice(Long id) {
       Cart cart = getCartById(id);
       return cart.getTotalPrice();
    }
}
