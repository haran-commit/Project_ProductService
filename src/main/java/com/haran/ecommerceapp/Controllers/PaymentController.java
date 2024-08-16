package com.haran.ecommerceapp.Controllers;

import com.haran.ecommerceapp.DTOs.InitiatePaymentRequestDto;
import com.haran.ecommerceapp.services.Payment.PaymentService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

   private PaymentService razorpayPaymentservice;
   private PaymentService stripePaymentservice;

    PaymentController(@Qualifier("razorpay") PaymentService razorpayPaymentservice,
                      PaymentService stripePaymentservice){
        this.razorpayPaymentservice = razorpayPaymentservice;
        this.stripePaymentservice = stripePaymentservice;
    }

    @PostMapping("/")
    public String initiatePayment(@RequestBody InitiatePaymentRequestDto requestDto){
        String payment = null;
      try{
          payment =  razorpayPaymentservice.doPayment(requestDto.getOrderID(),requestDto.getName(), requestDto.getAmount());
      }
      catch (Exception e){
          System.out.println(e.getMessage());
      }
      return payment;
    }


    private int  choosePaymentGateway(){
        //write your own logic to use both payment gateway wisely
        return 1;
    }
}
