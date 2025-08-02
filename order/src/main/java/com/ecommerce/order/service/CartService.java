package com.ecommerce.order.service;


import com.ecommerce.order.model.CartItem;
import com.ecommerce.order.dto.CartItemRequest;
import com.ecommerce.order.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class CartService {


    private final CartRepository cartRepository;
//    private final ProductRepository productRepository;
//    private final UserRepository userRepository;

    public Boolean addToCart(String userId, CartItemRequest request) {

//        Optional<Product> productOpt = productRepository.findById(request.getProductId());
//        if (productOpt.isEmpty()) {
//            return false;
//        }
//        Product product = productOpt.get();
//        if (product.getStockQuantity() < request.getQuantity()) {
//            return false;
//        }
//
//        Optional<User> userOpt = userRepository.findById(Long.valueOf(userId));
//        if (userOpt.isEmpty()) {
//            return false;
//        }
//
//        User user = userOpt.get();

        CartItem existingCartItem = cartRepository.findByUserIdAndProductId(userId, request.getProductId());
        if (existingCartItem != null) {
            //update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + request.getQuantity());
            existingCartItem.setPrice(BigDecimal.valueOf(1000));
            cartRepository.save(existingCartItem);
        } else {
            //create new cart item
            CartItem cartItem = new CartItem();
            cartItem.setUserId(userId);
            cartItem.setProductId(request.getProductId());
            cartItem.setQuantity(request.getQuantity());
            cartItem.setPrice(BigDecimal.valueOf(1000));
            cartRepository.save(cartItem);
        }
        return true;
    }

    public boolean deleteItemFromCart(String userId, String productId) {

      CartItem cartItem= cartRepository.findByUserIdAndProductId(userId,productId);


        if (cartItem !=null) {
            cartRepository.delete(cartItem);
            return true;
        }

        return false;
    }

    public List<CartItem> getCart(String userId) {

        return cartRepository.findByUserId(userId);
    }

    public void clearCart(String userId) {

       cartRepository.deleteByUserId(userId);

    }
}
