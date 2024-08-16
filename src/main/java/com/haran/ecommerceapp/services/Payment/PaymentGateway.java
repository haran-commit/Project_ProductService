package com.haran.ecommerceapp.services.Payment;

public interface PaymentGateway {

    String generatePaymentLink(String orderID,String name,Long amount);

    boolean validatePayment(Long orderId);
}
