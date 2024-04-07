package com.haran.ecommerceapp;

import com.haran.ecommerceapp.repositories.Projection.ProductProjection;
import com.haran.ecommerceapp.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class EcommerceappApplicationTests {

//    @Autowired
//    private ProductRepository productRepository;

//    public EcommerceappApplicationTests (ProductRepository productRepository){
//        this.productRepository = productRepository;
//    }

    @Test
    void contextLoads() {
    }

//    @Test
//    void Querytest(){
//      List<ProductProjection> pros =  productRepository.findallTitleAndIdbyCategory(
//              "accessories");
////    System.out.println(pros.get(0).getId());
////        System.out.println(pros.get(0).getTitle());
//    }

}
