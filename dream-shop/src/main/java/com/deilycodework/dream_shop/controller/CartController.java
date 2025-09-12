package com.deilycodework.dream_shop.controller;

import com.deilycodework.dream_shop.model.Cart;
import com.deilycodework.dream_shop.response.ApiResponse;
import com.deilycodework.dream_shop.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cart")
public class CartController {
    private final CartService cartService;

    @GetMapping("/get/{id}")

    public ResponseEntity<ApiResponse> getCart(@PathVariable Long id) {
        try {
            Cart cart = cartService.getCartById(id);
            return ResponseEntity.ok(new ApiResponse("Cart retrieved successfully", cart));

        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/total-price/{id}")
    public ResponseEntity<ApiResponse> getCartTotalPrice(@PathVariable Long id) {
        try {
            BigDecimal totalPrice = cartService.getCartTotalPrice(id);
            return ResponseEntity.ok(new ApiResponse("Total price retrieved successfully", totalPrice));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND)
                    .body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/clear/{id}")
    public ResponseEntity<ApiResponse> clearCart(@PathVariable Long id) {
        try {
            cartService.clearCartById(id);
            return ResponseEntity.ok(new ApiResponse("Cart cleared successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(
                    new ApiResponse(e.getMessage(), null)
            );
        }
    }
}
