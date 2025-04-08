package com.ecommerce.modal;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private int mrpPrice;
    private int sellingPrice;
    private int discountPercentage;
    private int quantity;
    private String color;

    @ElementCollection
    private List<String> images = new ArrayList<>();
    private int numOfRatings;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Seller seller;

    private LocalDate createdAt;
    private String sizes;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Product() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return mrpPrice == product.mrpPrice && sellingPrice == product.sellingPrice && discountPercentage == product.discountPercentage && quantity == product.quantity && numOfRatings == product.numOfRatings && Objects.equals(id, product.id) && Objects.equals(title, product.title) && Objects.equals(description, product.description) && Objects.equals(color, product.color) && Objects.equals(images, product.images) && Objects.equals(category, product.category) && Objects.equals(seller, product.seller) && Objects.equals(createdAt, product.createdAt) && Objects.equals(sizes, product.sizes) && Objects.equals(reviews, product.reviews);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, mrpPrice, sellingPrice, discountPercentage, quantity, color, images, numOfRatings, category, seller, createdAt, sizes, reviews);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMrpPrice() {
        return mrpPrice;
    }

    public void setMrpPrice(int mrpPrice) {
        this.mrpPrice = mrpPrice;
    }

    public int getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(int sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Product(Long id, String title, String description, int mrpPrice, int sellingPrice, int discountPercentage, int quantity, String color, List<String> images, int numOfRatings, Category category, Seller seller, LocalDate createdAt, String sizes, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.mrpPrice = mrpPrice;
        this.sellingPrice = sellingPrice;
        this.discountPercentage = discountPercentage;
        this.quantity = quantity;
        this.color = color;
        this.images = images;
        this.numOfRatings = numOfRatings;
        this.category = category;
        this.seller = seller;
        this.createdAt = createdAt;
        this.sizes = sizes;
        this.reviews = reviews;
    }
}
