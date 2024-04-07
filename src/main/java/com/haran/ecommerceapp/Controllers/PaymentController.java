package com.haran.ecommerceapp.Controllers;

import com.haran.ecommerceapp.DTOs.InitiatePaymentRequestDto;
import com.haran.ecommerceapp.DTOs.PaymentResponse;
import com.haran.ecommerceapp.services.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

   private PaymentService razorpayPaymentservice;
   private PaymentService stripePaymentservice;

    PaymentController(@Qualifier("razorpay") PaymentService razorpayPaymentservice,
                      @Qualifier("stripe") PaymentService stripePaymentservice){
        this.razorpayPaymentservice = razorpayPaymentservice;
        this.stripePaymentservice = stripePaymentservice;
    }

    @PostMapping("/payment")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto){
       int paymentGatewayOption = choosePaymentGateway();
       switch (paymentGatewayOption){
           case 1 : return razorpayPaymentservice.doPayment(requestDto.getEmail(), requestDto.getPhoneNumber(), requestDto.getAmount(), requestDto.getOrderID());
           case 2 : return stripePaymentservice.doPayment(requestDto.getEmail(), requestDto.getPhoneNumber(), requestDto.getAmount(), requestDto.getOrderID());
       }

        return null;
    }


    private int  choosePaymentGateway(){
        //write your own logic to use both payment gateway wisely
        return 1;
    }
}
