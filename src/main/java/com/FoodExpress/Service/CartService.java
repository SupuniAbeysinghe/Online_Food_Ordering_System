package com.FoodExpress.Service;

import com.FoodExpress.model.Cart;
import com.FoodExpress.model.CartItem;
import com.FoodExpress.model.Users;
import com.FoodExpress.request.AddCartItemRequest;

public interface CartService {
    public CartItem addItemToCart(AddCartItemRequest req, String jwt)throws Exception;
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity)throws Exception;
    public Cart removeItemFromCart(Long cartItemId, String jwt)throws Exception;
    public Long calculateCartTotal (Cart cart)throws Exception;
    public Cart findCartById(Long id)throws Exception;
    public Cart findCartByUserId(String jwt)throws Exception;
    public Cart clearCart(String jwt)throws Exception;

}
