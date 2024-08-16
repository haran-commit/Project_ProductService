package com.haran.ecommerceapp.Controllers;
import com.haran.ecommerceapp.DTOs.CreateProductRequestDto;
import com.haran.ecommerceapp.DTOs.ErrorDTO;
import com.haran.ecommerceapp.DTOs.FakeStoreProductDto;
import com.haran.ecommerceapp.Exceptions.ProductNotFoundException;
import com.haran.ecommerceapp.models.Category;
import com.haran.ecommerceapp.models.Product;
import com.haran.ecommerceapp.services.FakeStoreProductService;
import com.haran.ecommerceapp.services.ProductService;
import com.haran.ecommerceapp.services.SelfProductSerivce;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductRequestDto request) throws Exception {

        Product product =  productService.createProduct(
                request.getTitle(),
                request.getDescription(),
                request.getCategory(),
                request.getPrice(),
                request.getImage()
        );
        return  new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/products/{ID}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("ID") Long ProductID) throws ProductNotFoundException {
        Product product =  productService.getSingleProduct(ProductID);
        return new ResponseEntity<>(product,HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam("Pagenumber") int Pagenumber,
                                                       @RequestParam("Pagesize") int Pagesize){
        Page<Product> products = productService.getProducts(Pagenumber,Pagesize);
//        throw new RuntimeException();

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> getAllCategory(){

        List<Category> categories = productService.getAllCategory();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @DeleteMapping("/products/{id}")
    public void delete(@PathVariable("id") long id)throws ProductNotFoundException{

        productService.deleteProduct(id);
    }
    @PutMapping("/products/{id}")
    public  ResponseEntity<Product> updateProduct(@PathVariable("id") long id,
                                  @RequestBody CreateProductRequestDto update) {
        Product product =  productService.updateProduct(id,
                update.getDescription(),update.getTitle(),update.getPrice(),
                update.getImage(),update.getCategory());

        return new ResponseEntity<>(product,HttpStatus.OK);

    }
    @GetMapping("/products/category/{category}")
    public ResponseEntity<List<Product>> getAllCategoryWiseProduct(@PathVariable("category") String category) throws ProductNotFoundException{
            List<Product> products =  productService.getAllCategoryWiseProduct(category);
            return  new ResponseEntity<>(products,HttpStatus.OK);
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
