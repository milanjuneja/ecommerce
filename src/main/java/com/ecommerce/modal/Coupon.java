package com.ecommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

@Entity
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private double discountPercentage;

    private LocalDate validityStartDate;

    private LocalDate validityEndDate;

    private double minimumOrderValue;

    private boolean isActive=true;

    @ManyToMany(mappedBy = "usedCoupons")
    private Set<User> usedByUsers = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coupon coupon = (Coupon) o;
        return Double.compare(coupon.discountPercentage, discountPercentage) == 0 && Double.compare(coupon.minimumOrderValue, minimumOrderValue) == 0 && isActive == coupon.isActive && Objects.equals(id, coupon.id) && Objects.equals(code, coupon.code) && Objects.equals(validityStartDate, coupon.validityStartDate) && Objects.equals(validityEndDate, coupon.validityEndDate) && Objects.equals(usedByUsers, coupon.usedByUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, discountPercentage, validityStartDate, validityEndDate, minimumOrderValue, isActive, usedByUsers);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public LocalDate getValidityStartDate() {
        return validityStartDate;
    }

    public void setValidityStartDate(LocalDate validityStartDate) {
        this.validityStartDate = validityStartDate;
    }

    public LocalDate getValidityEndDate() {
        return validityEndDate;
    }

    public void setValidityEndDate(LocalDate validityEndDate) {
        this.validityEndDate = validityEndDate;
    }

    public double getMinimumOrderValue() {
        return minimumOrderValue;
    }

    public void setMinimumOrderValue(double minimumOrderValue) {
        this.minimumOrderValue = minimumOrderValue;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<User> getUsedByUsers() {
        return usedByUsers;
    }

    public void setUsedByUsers(Set<User> usedByUsers) {
        this.usedByUsers = usedByUsers;
    }

    public Coupon() {
    }

    public Coupon(Long id, String code, double discountPercentage, LocalDate validityStartDate, LocalDate validityEndDate, double minimumOrderValue, boolean isActive, Set<User> usedByUsers) {
        this.id = id;
        this.code = code;
        this.discountPercentage = discountPercentage;
        this.validityStartDate = validityStartDate;
        this.validityEndDate = validityEndDate;
        this.minimumOrderValue = minimumOrderValue;
        this.isActive = isActive;
        this.usedByUsers = usedByUsers;
    }
}
