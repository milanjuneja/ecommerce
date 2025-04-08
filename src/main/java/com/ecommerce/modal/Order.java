package com.ecommerce.modal;

import com.ecommerce.domain.OrderStatus;
import com.ecommerce.domain.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderId;

    @ManyToOne
    private User user;

    private Long sellerId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne
    private Address shipmentAddress;

    @Embedded
    private PaymentDetails paymentDetails = new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;

    private OrderStatus orderStatus;

    private int totalItem;

    @Column(insertable = false, updatable = false)
    private PaymentStatus paymentStatus = PaymentStatus.PENDING;

    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Double.compare(order.totalMrpPrice, totalMrpPrice) == 0 && totalItem == order.totalItem && Objects.equals(id, order.id) && Objects.equals(orderId, order.orderId) && Objects.equals(user, order.user) && Objects.equals(sellerId, order.sellerId) && Objects.equals(orderItems, order.orderItems) && Objects.equals(shipmentAddress, order.shipmentAddress) && Objects.equals(paymentDetails, order.paymentDetails) && Objects.equals(totalSellingPrice, order.totalSellingPrice) && Objects.equals(discount, order.discount) && orderStatus == order.orderStatus && paymentStatus == order.paymentStatus && Objects.equals(orderDate, order.orderDate) && Objects.equals(deliverDate, order.deliverDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderId, user, sellerId, orderItems, shipmentAddress, paymentDetails, totalMrpPrice, totalSellingPrice, discount, orderStatus, totalItem, paymentStatus, orderDate, deliverDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Address getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(Address shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public double getTotalMrpPrice() {
        return totalMrpPrice;
    }

    public void setTotalMrpPrice(double totalMrpPrice) {
        this.totalMrpPrice = totalMrpPrice;
    }

    public Integer getTotalSellingPrice() {
        return totalSellingPrice;
    }

    public void setTotalSellingPrice(Integer totalSellingPrice) {
        this.totalSellingPrice = totalSellingPrice;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDateTime getDeliverDate() {
        return deliverDate;
    }

    public void setDeliverDate(LocalDateTime deliverDate) {
        this.deliverDate = deliverDate;
    }

    public Order() {
    }

    public Order(Long id, String orderId, User user, Long sellerId, List<OrderItem> orderItems, Address shipmentAddress, PaymentDetails paymentDetails, double totalMrpPrice, Integer totalSellingPrice, Integer discount, OrderStatus orderStatus, int totalItem, PaymentStatus paymentStatus, LocalDateTime orderDate, LocalDateTime deliverDate) {
        this.id = id;
        this.orderId = orderId;
        this.user = user;
        this.sellerId = sellerId;
        this.orderItems = orderItems;
        this.shipmentAddress = shipmentAddress;
        this.paymentDetails = paymentDetails;
        this.totalMrpPrice = totalMrpPrice;
        this.totalSellingPrice = totalSellingPrice;
        this.discount = discount;
        this.orderStatus = orderStatus;
        this.totalItem = totalItem;
        this.paymentStatus = paymentStatus;
        this.orderDate = orderDate;
        this.deliverDate = deliverDate;
    }
}
