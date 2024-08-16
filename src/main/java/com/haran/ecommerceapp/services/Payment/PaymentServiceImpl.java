package com.haran.ecommerceapp.services.Payment;

import com.razorpay.RazorpayClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("razorpay")
public class PaymentServiceImpl implements PaymentService {

    private PaymentGateway paymentGateway;

    public PaymentServiceImpl(@Qualifier("razor") PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }
    @Override
    public String doPayment(String orderId,String name,long amount) {

       return paymentGateway.generatePaymentLink(orderId,
               name,
               amount);
    }


}
