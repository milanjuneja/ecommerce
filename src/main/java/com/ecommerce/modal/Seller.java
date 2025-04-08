package com.ecommerce.modal;

import com.ecommerce.domain.AccountStatus;
import com.ecommerce.domain.USER_ROLE;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String sellerName;

    private String mobile;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Embedded
    private BusinessDetails businessDetails = new BusinessDetails();
    @Embedded
    private BankDetails bankDetails = new BankDetails();

    @OneToOne(cascade = CascadeType.ALL)
    private Address pickUpAddress = new Address();

    private String GSTIN;

    private USER_ROLE role=USER_ROLE.ROLE_SELLER;
    private boolean isEmailVerified = false;
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;

    public Seller() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return isEmailVerified == seller.isEmailVerified && Objects.equals(id, seller.id) && Objects.equals(sellerName, seller.sellerName) && Objects.equals(mobile, seller.mobile) && Objects.equals(email, seller.email) && Objects.equals(password, seller.password) && Objects.equals(businessDetails, seller.businessDetails) && Objects.equals(bankDetails, seller.bankDetails) && Objects.equals(pickUpAddress, seller.pickUpAddress) && Objects.equals(GSTIN, seller.GSTIN) && role == seller.role && accountStatus == seller.accountStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, sellerName, mobile, email, password, businessDetails, bankDetails, pickUpAddress, GSTIN, role, isEmailVerified, accountStatus);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public BusinessDetails getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(BusinessDetails businessDetails) {
        this.businessDetails = businessDetails;
    }

    public BankDetails getBankDetails() {
        return bankDetails;
    }

    public void setBankDetails(BankDetails bankDetails) {
        this.bankDetails = bankDetails;
    }

    public Address getPickUpAddress() {
        return pickUpAddress;
    }

    public void setPickUpAddress(Address pickUpAddress) {
        this.pickUpAddress = pickUpAddress;
    }

    public String getGSTIN() {
        return GSTIN;
    }

    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    public USER_ROLE getRole() {
        return role;
    }

    public void setRole(USER_ROLE role) {
        this.role = role;
    }

    public boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        isEmailVerified = emailVerified;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Seller(Long id, String sellerName, String mobile, String email, String password, BusinessDetails businessDetails, BankDetails bankDetails, Address pickUpAddress, String GSTIN, USER_ROLE role, boolean isEmailVerified, AccountStatus accountStatus) {
        this.id = id;
        this.sellerName = sellerName;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.businessDetails = businessDetails;
        this.bankDetails = bankDetails;
        this.pickUpAddress = pickUpAddress;
        this.GSTIN = GSTIN;
        this.role = role;
        this.isEmailVerified = isEmailVerified;
        this.accountStatus = accountStatus;
    }
}
