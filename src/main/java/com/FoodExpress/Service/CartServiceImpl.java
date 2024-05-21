package com.FoodExpress.Service;

import com.FoodExpress.model.Cart;
import com.FoodExpress.model.CartItem;
import com.FoodExpress.model.Food;
import com.FoodExpress.model.Users;
import com.FoodExpress.repository.CartItemRepository;
import com.FoodExpress.repository.CartRepository;
import com.FoodExpress.repository.FoodRepository;
import com.FoodExpress.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private FoodService foodService;
    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);

        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(users.getId());

        for(CartItem cartItem: cart.getItems()){
            if(cartItem.getFood().equals(food)){
                int newQuantity=cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(),newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity() *food.getPrice());

        CartItem savesCartItem = cartItemRepository.save(newCartItem);

        cart.getItems().add(savesCartItem);

        return savesCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);


        item.setTotalPrice(item.getFood().getPrice()*quantity);
        return cartItemRepository.save(item);
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);

        Cart cart = cartRepository.findByCustomerId(users.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()){
            throw new Exception("cart item not found");
        }

        CartItem item = cartItemOptional.get();
        cart.getItems().remove(item);

        return cartRepository.save(cart);
    }

    @Override
    public Long calculateCartTotal(Cart cart) throws Exception {

        Long total=0L;

        for (CartItem cartItem :cart.getItems()){
            total+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return total;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
        Optional<Cart> optionalCart=cartRepository.findById(id);
        if (optionalCart.isEmpty()){
            throw new Exception("cart is not found with id "+ id);
        }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);

        return cartRepository.findByCustomerId(users.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        Users users = userService.findUserByJwtToken(jwt);
        Cart cart=findCartByUserId(jwt);
        cart.getItems().clear();
        return cartRepository.save(cart);
    }
}
