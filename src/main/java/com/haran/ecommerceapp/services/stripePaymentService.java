package com.haran.ecommerceapp.services;

import com.haran.ecommerceapp.DTOs.PaymentResponse;
import org.springframework.stereotype.Service;

@Service("stripe")
public class stripePaymentService implements PaymentService {

    @Override
    public String doPayment(String email, String phone, long amount, String orderId) {
        return null;
    }
}
