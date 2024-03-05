package com.haran.ecommerceapp.DTOs;

import com.haran.ecommerceapp.models.Category;
import com.haran.ecommerceapp.models.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private long id;
    private String title;
    private String description;
    private String image;
    private String category;
    private double price;

    public Product toProduct(){
        Product product = new Product();
        product.setId(id);
        product.setTitle(title);
        product.setDescription(description);
        product.setPrice(price);
        product.setImageURL(image);


        Category productCategory = new Category();
        productCategory.setTitle(category);

        product.setCategory(productCategory);

        return product;
    }

//    public  Category toCategory(){
//        Category category1 = new Category();
//
//        category1.setTitle();
//    }

}
