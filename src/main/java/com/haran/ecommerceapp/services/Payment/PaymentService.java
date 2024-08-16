package com.haran.ecommerceapp.services.Payment;


public interface PaymentService {

    String doPayment(String orderId, String name, long amount);


}
