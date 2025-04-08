package com.ecommerce.controller;

import com.ecommerce.config.JwtProvider;
import com.ecommerce.domain.AccountStatus;
import com.ecommerce.exception.SellerException;
import com.ecommerce.modal.Seller;
import com.ecommerce.modal.SellerReport;
import com.ecommerce.modal.VerificationCode;
import com.ecommerce.request.LoginRequest;
import com.ecommerce.response.AuthResponse;
import com.ecommerce.repository.VerificationCodeRepository;
import com.ecommerce.service.AuthService;
import com.ecommerce.service.EmailService;
import com.ecommerce.service.SellerReportService;
import com.ecommerce.service.SellerService;
import com.ecommerce.utils.OtpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sellers")
public class SellerController {

    @Autowired
    private SellerService service;
    @Autowired
    private VerificationCodeRepository verificationCodeRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private SellerService sellerService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private SellerReportService sellerReportService;


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> loginSeller(@RequestBody LoginRequest req) throws Exception {
        req.setEmail("seller_" + req.getEmail());
        AuthResponse authResponse = authService.signIn(req);

        return new ResponseEntity<>(authResponse, HttpStatus.OK);

    }

    @PatchMapping("/verify/{otp}")
    public ResponseEntity<Seller> verifySellerEmail(@PathVariable String otp) throws Exception {
        VerificationCode verificationCode = verificationCodeRepository.findByOtp(otp);
        if (verificationCode == null || !verificationCode.getOtp().equals(otp)) {
            throw new Exception("Wrong otp....");
        }
        return new ResponseEntity<>(sellerService.verifyEmail(verificationCode.getEmail(), otp), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Seller> createSeller(@RequestBody Seller seller) throws Exception{
        Seller savedSeller = sellerService.createSeller(seller);
        String otp = OtpUtil.generateOtp();
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setOtp(otp);
        verificationCode.setEmail(seller.getEmail());
        verificationCodeRepository.save(verificationCode);

        String subject = "Email verification Code";
        String text = "verify your account using this link ";
        String frontEndUrl = "http://localhost:3000/verify-seller/";
        emailService.sendVerificationOtpEmail(seller.getEmail(), verificationCode.getOtp(), subject, text + frontEndUrl);
        return new ResponseEntity<>(savedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seller> getSellerById(@PathVariable Long id) throws SellerException {
        return new ResponseEntity<>(sellerService.getSellerById(id), HttpStatus.OK);
    }

    @GetMapping("/profile")
    public ResponseEntity<Seller> getSellerByJwt(@RequestHeader("Authorization") String jwt) throws Exception {
        return new ResponseEntity<>(sellerService.getSellerProfileFromJwt(jwt), HttpStatus.OK);
    }

    @GetMapping("/report")
    public ResponseEntity<SellerReport> getSellerReport(@RequestHeader("Authorization") String jwt) throws Exception {
        Seller seller = sellerService.getSellerProfileFromJwt(jwt);
        return new ResponseEntity<>(sellerReportService.getSellerReport(seller), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Seller>> getAllSeller(@RequestParam(required = false) AccountStatus status){
        return new ResponseEntity<>(sellerService.getAllSellers(status), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<Seller> updateSeller(@RequestHeader("Authorization") String jwt,
                                               @RequestBody Seller seller) throws Exception {
        Seller profile = sellerService.getSellerProfileFromJwt(jwt);
        return new ResponseEntity<>(sellerService.updateSeller(profile.getId(), seller), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSeller(@PathVariable Long id) throws Exception {
        sellerService.deleteSeller(id);
        return ResponseEntity.noContent().build();
    }



}
