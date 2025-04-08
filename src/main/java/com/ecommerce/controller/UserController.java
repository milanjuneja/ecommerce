package com.ecommerce.controller;

import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.modal.User;
import com.ecommerce.request.SignupRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/users/profile")
    public ResponseEntity<User> createUserHandler(
            @RequestHeader("Authorization") String jwt)
            throws Exception {

        return new ResponseEntity<>(userService.findUserByJwtToken(jwt), HttpStatus.OK);



    }

}
