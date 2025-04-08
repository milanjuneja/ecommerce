package com.ecommerce.controller;

import com.ecommerce.modal.Cart;
import com.ecommerce.modal.CartItems;
import com.ecommerce.modal.Product;
import com.ecommerce.modal.User;
import com.ecommerce.request.AddItemRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.service.CartItemService;
import com.ecommerce.service.CartService;
import com.ecommerce.service.ProductService;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserByJwtToken(jwt);
        return new ResponseEntity<>(cartService.findUserCart(user), HttpStatus.OK);
    }

    @PutMapping("/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestHeader("Authorization") String jwt,
                                                   @RequestBody AddItemRequest req) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        Product product = productService.findProductById(req.getProductId());
        cartService.addCartItem(user, product, req.getSize(), req.getQuantity());
        ApiResponse res = new ApiResponse();
        res.setMessage("Item added to cart successfully");
        return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
    }
    @DeleteMapping("/item/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(
            @PathVariable Long cartItemId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        cartItemService.removeCartItem(user.getId(), cartItemId);

        ApiResponse response = new ApiResponse();
        response.setMessage("Item removed from cart successfully");

        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);

    }

    @PutMapping("/item/{cartItemId}")
    public ResponseEntity<CartItems> updateCartItem(
        @PathVariable Long cartItemId,
        @RequestBody CartItems cartItems,
        @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserByJwtToken(jwt);
        CartItems updatedCartItem = null;
        if(cartItems.getQuantity() > 0){
            updatedCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItems);
        }

        return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);

    }
}
