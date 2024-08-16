package com.haran.ecommerceapp.services;

import com.haran.ecommerceapp.Exceptions.ProductNotFoundException;
import com.haran.ecommerceapp.models.Category;
import com.haran.ecommerceapp.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long productId) throws ProductNotFoundException;
    Page<Product> getProducts(int PageNumber, int PageSize);
    Product createProduct(String title,String description,String category,double price,
                          String image) throws Exception;

    List<Category> getAllCategory();
   void deleteProduct(long id) throws ProductNotFoundException;

   Product updateProduct(long id,String desc,String title,double price,
                      String image,String category);

   List<Product> getAllCategoryWiseProduct(String jew);
}