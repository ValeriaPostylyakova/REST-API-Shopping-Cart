package com.deilycodework.dream_shop.controller;

import com.deilycodework.dream_shop.model.CartItem;
import com.deilycodework.dream_shop.response.ApiResponse;
import com.deilycodework.dream_shop.service.cartItem.ICartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("${api.prefix}/cartItems")
@RequiredArgsConstructor
public class CartItemController {
    private final ICartItemService cartItemService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam Long cartId, @RequestParam Long productId, @RequestParam Integer quantity) {
        try {
            cartItemService.addItemToCart(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item added to cart", null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/item/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestParam Long cartId, @RequestParam Long productId) {
        try {
            cartItemService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Item removed from cart", null));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/item/get/{id}")
    public ResponseEntity<ApiResponse> getCartItem(@RequestParam Long cartId, @PathVariable Long productId) {
        try {
            CartItem cartItem = cartItemService.getCartItem(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Item retrieved successfully", cartItem));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }


    @PutMapping("/item/update/{id}")
    public ResponseEntity<ApiResponse> updateCartItemQuantity(@RequestParam Long cartId, @PathVariable Long productId, @RequestParam Integer quantity) {
        try {
            cartItemService.updateCartItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok(new ApiResponse("Item quantity updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
