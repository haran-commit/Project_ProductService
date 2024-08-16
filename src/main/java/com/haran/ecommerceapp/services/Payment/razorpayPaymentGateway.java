package com.haran.ecommerceapp.services.Payment;

import com.haran.ecommerceapp.configs.RazorpayConfig;
import com.razorpay.RazorpayClient;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Service("razor")
public class razorpayPaymentGateway implements PaymentGateway {

    public RazorpayClient razorpayClient;

    public razorpayPaymentGateway(RazorpayClient razorpayClient){
        this.razorpayClient = razorpayClient;
    }
    @Override
    public String generatePaymentLink(String orderID, String name, Long amount) {

//        JSONObject paymentLinkRequest = new JSONObject();
//        paymentLinkRequest.put("amount", 1000); // 10.00
//        paymentLinkRequest.put("currency","INR");
////        paymentLinkRequest.put("accept_partial",true);
////        paymentLinkRequest.put("first_min_partial_amount",100);
//        paymentLinkRequest.put("expire_by", 1715396752); //Epoch
//        paymentLinkRequest.put("reference_id", orderId.toString());
//        paymentLinkRequest.put("description","Payment for Scaler class - 11th May");
//        JSONObject customer = new JSONObject();
//        customer.put("name","Rishi");
//        customer.put("contact","7015608331");
//        customer.put("email","rishibawankule@gmail.com");
//        paymentLinkRequest.put("customer",customer);
//        JSONObject notify = new JSONObject();
//        notify.put("sms",true);
//        notify.put("email",true);
//        paymentLinkRequest.put("notify",notify);
//        paymentLinkRequest.put("reminder_enable",true);
//        JSONObject notes = new JSONObject();
//        notes.put("policy_name","Some Random Policy");
//        paymentLinkRequest.put("notes",notes);
//        paymentLinkRequest.put("callback_url","https://www.scaler.com/");
//        paymentLinkRequest.put("callback_method","get");
//
//        PaymentLink payment = razorpayClient.paymentLink.create(paymentLinkRequest);
//
       //return payment.toString();
        return null;
    }

    @Override
    public boolean validatePayment(Long orderId) {
        return false;
    }


}
