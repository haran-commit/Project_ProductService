package com.haran.ecommerceapp.services.Payment;

import org.springframework.stereotype.Service;

@Service("stripe")
public class stripePaymentGateway implements PaymentGateway{

    @Override
    public String generatePaymentLink(String orderID, String name, Long amount) {
        return null;
    }

    @Override
    public boolean validatePayment(Long orderId) {
        return false;
    }
}
