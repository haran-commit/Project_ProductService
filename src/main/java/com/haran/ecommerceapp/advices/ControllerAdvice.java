package com.haran.ecommerceapp.advices;

import com.haran.ecommerceapp.DTOs.ErrorDTO;
import com.haran.ecommerceapp.Exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleProductNotFound(ProductNotFoundException exception){

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage(exception.getMessage());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
