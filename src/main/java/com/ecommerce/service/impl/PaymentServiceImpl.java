package com.ecommerce.service.impl;

import com.ecommerce.domain.PaymentOrderStatus;
import com.ecommerce.domain.PaymentStatus;
import com.ecommerce.modal.Order;
import com.ecommerce.modal.PaymentOrder;
import com.ecommerce.modal.User;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.PaymentOrderRepository;
import com.ecommerce.service.PaymentService;
import com.razorpay.Payment;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentOrderRepository paymentOrderRepository;
    @Autowired
    private OrderRepository orderRepository;

    private final String API_KEY = "";
    private final String API_SECRET_KEY = "";

    private final String STRIPE_SECRET_KEY = "stripe";


    @Override
    public PaymentOrder createOrder(User user, Set<Order> orders) {
        Long amount = orders.stream()
                .mapToLong(Order::getTotalSellingPrice).sum();
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setAmount(amount);
        paymentOrder.setOrders(orders);
        paymentOrder.setUser(user);

        return paymentOrderRepository.save(paymentOrder);

    }

    @Override
    public PaymentOrder getPaymentOrderById(Long orderId) throws Exception {
        return paymentOrderRepository.findById(orderId).orElseThrow(() ->
                new Exception("Payment order not found"));
    }

    @Override
    public PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception {
        PaymentOrder byPaymentLinkId = paymentOrderRepository.findByPaymentLinkId(orderId);
        if(byPaymentLinkId == null)
            throw new Exception("Payment order not found with provided payment link id -> " + orderId);
        return byPaymentLinkId;
    }

    @Override
    public Boolean proceedPaymentOrder(PaymentOrder paymentOrder, String paymentId, String paymentLinkId) throws RazorpayException {
        if(paymentOrder.getPaymentOrderStatus().equals(PaymentOrderStatus.PENDING)){
            RazorpayClient razorPay = new RazorpayClient(API_KEY, API_SECRET_KEY);
            Payment payment = razorPay.payments.fetch(paymentId);
            String status = payment.get("status");
            if(status.equals("captured")){
                Set<Order> orders = paymentOrder.getOrders();
                for (Order order: orders) {
                    order.setPaymentStatus(PaymentStatus.COMPLETED);
                    orderRepository.save(order);
                }
                paymentOrder.setPaymentOrderStatus(PaymentOrderStatus.SUCCESS);
                paymentOrderRepository.save(paymentOrder);
                return true;
            }
            paymentOrder.setPaymentOrderStatus(PaymentOrderStatus.FAILED);
            paymentOrderRepository.save(paymentOrder);
        }

        return false;
    }

    @Override
    public PaymentLink createRazorpayPaymentLink(User user, Long amount, Long orderId) throws RazorpayException {
        amount = amount * 100;
        try {
            RazorpayClient razorPay = new RazorpayClient(API_KEY, API_SECRET_KEY);
            JSONObject paymentLinkRequest = new JSONObject();
            paymentLinkRequest.put("amount", amount);
            paymentLinkRequest.put("currency", "INR");

            JSONObject customer = new JSONObject();
            customer.put("name", user.getFirstName() + " " + user.getLastName());
            customer.put("email", user.getEmail());
            paymentLinkRequest.put("customer", customer);

            JSONObject notify = new JSONObject();
            notify.put("email", true);
            paymentLinkRequest.put("notify", notify);

            paymentLinkRequest.put("callback_url", "http://localhost:5173/payment-success/"+orderId);
            paymentLinkRequest.put("callback_method", "get");

            return razorPay.paymentLink.create(paymentLinkRequest);
//            String paymentLinkUrl = paymentLink.get("short_url");
//            String paymentLinkId = paymentLink.get("id");
        }
        catch (Exception e){
            throw new RazorpayException(e.getMessage());
        }
    }

    @Override
    public String createStripePaymentLink(User user, Long amount, Long orderId) throws StripeException {
        Stripe.apiKey = STRIPE_SECRET_KEY;

        SessionCreateParams params = SessionCreateParams.builder()
                .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl("http://localhost:3000/payment-success/"+orderId)
                .setCancelUrl("http://localhost:3000/payment-cancel/")
                .addLineItem(SessionCreateParams.LineItem.builder()
                        .setQuantity(1L)
                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
                                .setCurrency("inr")
                                .setUnitAmount(amount*100)
                                .setProductData(
                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                .setName("Milan Payment")
                                                .build()
                                ).build()
                        ).build()
                )
                .build();
        Session session = Session.create(params);

        return session.getUrl();
    }
}
