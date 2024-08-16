package com.haran.ecommerceapp.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InitiatePaymentRequestDto {

    private  String orderID;
    private String name;

    private long amount;

}
