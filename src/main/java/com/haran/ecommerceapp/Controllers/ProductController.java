package com.haran.ecommerceapp.Controllers;
import com.haran.ecommerceapp.DTOs.CreateProductRequestDto;
import com.haran.ecommerceapp.DTOs.ErrorDTO;
import com.haran.ecommerceapp.DTOs.FakeStoreProductDto;
import com.haran.ecommerceapp.Exceptions.ProductNotFoundException;
import com.haran.ecommerceapp.models.Category;
import com.haran.ecommerceapp.models.Product;
import com.haran.ecommerceapp.services.FakeStoreProductService;
import com.haran.ecommerceapp.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;
 //  private RestTemplate restTemplate;

    public ProductController(ProductService productService,RestTemplate restTemplate){
        this.productService = productService;
        //this.restTemplate = restTemplate;
    }

    @PostMapping("/products")
    public Product createProduct(@RequestBody CreateProductRequestDto request){

        return productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()

        );
    }

    @GetMapping("/products/{ID}")
    public Product getProductDetails(@PathVariable("ID") Long ProductID) throws ProductNotFoundException {
        return productService.getSingleProduct(ProductID);
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProduct(){

        List<Product> products = productService.getProducts();

//        throw new RuntimeException();
        ResponseEntity<List<Product>> response = new ResponseEntity<>(products, HttpStatus.NOT_FOUND);
       return response;

    }

    @GetMapping("/category")
    public List<Category> getAllCategory(){
        return  productService.getAllCategory();
    }
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") long id){
            productService.deleteProduct(id);
    }
    @PutMapping("/products/{id}")
    public  Product updateProduct(@PathVariable("id") long id,
                                  @RequestBody CreateProductRequestDto update){
        return productService.updateProduct(id,
                update.getDescription(),update.getTitle(),update.getPrice(),
                update.getImage(),update.getCategory());

    }
    @GetMapping("/products/category/{jew}")
    public List<Product> getAllCategoryWiseProduct(@PathVariable("jew") String jew){
            return productService.getAllCategoryWiseProduct(jew);
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity<ErrorDTO> handleProductNotFound(ProductNotFoundException exception){
//
//        ErrorDTO errorDTO = new ErrorDTO();
//        errorDTO.setMessage(exception.getMessage());
//
//        return new ResponseEntity<>(errorDTO,HttpStatus.NOT_FOUND);
//    }

    //this is local advice which is limited with only this controller
    // you can have global advice
}
