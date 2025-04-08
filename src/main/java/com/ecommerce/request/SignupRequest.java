package com.ecommerce.request;
import lombok.*;


public class SignupRequest {
    private String email;
    private String firstName;
    private String lastName;
    private String otp;

    public SignupRequest(String email, String firstName, String lastName, String otp) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.otp = otp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
