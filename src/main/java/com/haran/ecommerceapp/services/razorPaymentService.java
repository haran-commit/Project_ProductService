package com.haran.ecommerceapp.services;

import com.haran.ecommerceapp.DTOs.PaymentResponse;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayClient;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service("razorpay")
public class razorPaymentService implements PaymentService {

    private RazorpayClient razorpayClient;

    public razorPaymentService(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String doPayment(String email, String phone, long amount, String orderId) {

        try {
            JSONObject PaymentLinkRequest = new JSONObject();
            PaymentLinkRequest.put("amount", amount);
            PaymentLinkRequest.put("currency", "INR");
            PaymentLinkRequest.put("receipt", orderId);

            JSONObject Customer = new JSONObject();
            Customer.put("email", email);
            Customer.put("phone", phone);

            PaymentLinkRequest.put("Customer", Customer);

            JSONObject notify = new JSONObject();
            notify.put("sms", true);
            notify.put("email", true);
            PaymentLinkRequest.put("notify", notify);
            PaymentLinkRequest.put("CallbackURL", "https://domain.com/razorpayWebHook");
            PaymentLinkRequest.put("callback_method", "post");

            PaymentLink response = razorpayClient.paymentLink.create(PaymentLinkRequest);
            return response.toString();
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
