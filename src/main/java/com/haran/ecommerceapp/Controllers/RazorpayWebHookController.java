package com.haran.ecommerceapp.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PostMapping("/razorpayWebHook")
public class RazorpayWebHookController {

    @PostMapping("/")
    public ResponseEntity acceptWebHook(){

        return null;
    }
}
