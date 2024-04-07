package com.haran.ecommerceapp.services;

import com.haran.ecommerceapp.DTOs.PaymentResponse;


public interface PaymentService {

    String doPayment(String email,String phone,long amount,String orderId);
}
