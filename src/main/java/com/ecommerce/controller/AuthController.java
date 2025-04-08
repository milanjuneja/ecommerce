package com.ecommerce.controller;

import com.ecommerce.domain.USER_ROLE;
import com.ecommerce.modal.VerificationCode;
import com.ecommerce.request.LoginOtpRequest;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.response.ApiResponse;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.request.SignupRequest;
import com.ecommerce.service.AuthService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception {

        String jwt = authService.createUser(req);
        AuthResponse response = new AuthResponse();
        response.setJwt(jwt);
        response.setMessage("Register Success");
        response.setRole(USER_ROLE.ROLE_CUSTOMER);

        return new ResponseEntity<>(response, HttpStatus.CREATED);

    }

    @PostMapping("/send/login-signup-otp")
    public ResponseEntity<ApiResponse> sendOtpHandler(@RequestBody LoginOtpRequest req) throws Exception {

        authService.sendLoginOtp(req.getEmail(), req.getRole());
        ApiResponse response = new ApiResponse();

        response.setMessage("Otp sent successfully");


        return new ResponseEntity<>(response, HttpStatus.OK);

    }

    @PostMapping("/signing")
    public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception {

        AuthResponse authResponse = authService.signIn(req);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }
}
