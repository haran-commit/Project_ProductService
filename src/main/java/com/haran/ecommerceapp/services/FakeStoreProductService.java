package com.haran.ecommerceapp.services;

import com.haran.ecommerceapp.DTOs.FakeStoreProductDto;
import com.haran.ecommerceapp.Exceptions.ProductNotFoundException;
import com.haran.ecommerceapp.models.Category;
import com.haran.ecommerceapp.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("FakeStoreProductService")
public class FakeStoreProductService implements ProductService{
    private RestTemplate restTemplate;

    public FakeStoreProductService(RestTemplate restTemplate){

        this.restTemplate = restTemplate;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {
      ResponseEntity<FakeStoreProductDto> fakeStoreProductresponse =   restTemplate.getForEntity(
                "https://fakestoreapi.com/products/" + productId,
                FakeStoreProductDto.class
      );

        FakeStoreProductDto response = fakeStoreProductresponse.getBody();

        if(response == null){
            throw new  ProductNotFoundException("Product ID " + productId + " is not found");
        }
        return response.toProduct();
    }

    @Override
    public Page<Product> getProducts(int Pagenumber, int Pagesize) {
        return null;
    }

//    @Override
//    public List<Product> getProducts() {
//        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject(
//                "https://fakestoreapi.com/products",
//                FakeStoreProductDto[].class
//        );
//        List<Product> ans = new ArrayList<>();
//        for(FakeStoreProductDto fakeStoreProduct: fakeStoreProductDto){
//            ans.add(fakeStoreProduct.toProduct());
//        }
//        return ans;
//    }

    @Override
    public Product createProduct(
            String title,
            String description,
            String category,
            double price,
            String image) {
        FakeStoreProductDto fakeStoreProductdto = new FakeStoreProductDto();
        //which serves as a data transfer
        // object to hold product information before sending it to the API.
        fakeStoreProductdto.setTitle(title);
        fakeStoreProductdto.setDescription(description);
        fakeStoreProductdto.setCategory(category);
        fakeStoreProductdto.setImage(image);
        fakeStoreProductdto.setPrice(price);


        FakeStoreProductDto response = restTemplate.postForObject(
                "https://fakestoreapi.com/products",//URL
                fakeStoreProductdto,// request body
                FakeStoreProductDto.class //data type of response
        );
        return response.toProduct();
    }

    @Override
    public List<Category> getAllCategory() {

        List<String> fakeStoreCategoryDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories",
                List.class
        );
        List<Category> ans = new ArrayList<>();
        for( String fakeStoreCategory: fakeStoreCategoryDto){
            Category category = new Category();
                    category.setTitle(fakeStoreCategory);
            ans.add(category);
        }

        return ans;
    }

    @Override
    public void deleteProduct(long id) {

        restTemplate.delete(
            "https://fakestoreapi.com/products/" + id
        );
        //System.out.println("product" + id + "deleted");

    }

    @Override
    public Product updateProduct(long id, String desc, String title, double price, String image,String category) {

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();

        fakeStoreProductDto.setId(id);
        fakeStoreProductDto.setDescription(desc);
        fakeStoreProductDto.setTitle(title);
        fakeStoreProductDto.setPrice(price);
        fakeStoreProductDto.setImage(image);
        fakeStoreProductDto.setCategory(category);

        restTemplate.put(
                "https://fakestoreapi.com/products/" + id,
                fakeStoreProductDto
        );
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllCategoryWiseProduct(String jew) {
        FakeStoreProductDto[] fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + jew,
                FakeStoreProductDto[].class
        );

        List<Product> ans = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProduct: fakeStoreProductDto){
            ans.add(fakeStoreProduct.toProduct());
        }
        return ans;
    }


}
