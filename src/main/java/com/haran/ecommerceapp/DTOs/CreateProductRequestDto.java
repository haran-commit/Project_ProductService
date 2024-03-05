package com.haran.ecommerceapp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateProductRequestDto {
    private long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;



}
