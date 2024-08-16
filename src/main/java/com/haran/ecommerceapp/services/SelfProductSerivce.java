package com.haran.ecommerceapp.services;

import com.haran.ecommerceapp.Exceptions.ProductNotFoundException;
import com.haran.ecommerceapp.models.Category;
import com.haran.ecommerceapp.models.Product;
import com.haran.ecommerceapp.repositories.CategoryRepository;
import com.haran.ecommerceapp.repositories.ProductRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import java.util.List;
@Service("SelfProductService")
public class SelfProductSerivce implements ProductService{
   // @Autowired
   private ProductRepository productRepository;
    //@Autowired
   private CategoryRepository categoryRepository;

  public SelfProductSerivce(ProductRepository productRepository,CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundException {

        Product product =  productRepository.findByIdIs(productId);
        if(product == null){
            throw new ProductNotFoundException("Product not found with ID " + productId);
        }
        return product;
    }



    @Override
    public Page<Product> getProducts(int PageNumber,int pageSize) {

      Page<Product> pages =  productRepository.findAll(PageRequest.of(PageNumber,pageSize));
      return pages;
    }

    @Override
    public Product createProduct(String title, String description, String category, double price, String image) throws Exception{
            Product product = new Product();
            product.setTitle(title);
            product.setDescription(description);
            product.setPrice(price);
            product.setImageURL(image);

            Category categoryFromDatabase = categoryRepository.findByTitle(category);

            if(categoryFromDatabase==null){
                Category newcategory = new Category();
                        newcategory.setTitle(category);
                categoryFromDatabase = newcategory;//no need to add category to DB as
                                                        // cascade persist will create catego first
            }
            product.setCategory(categoryFromDatabase);

          Product saveProduct =   productRepository.save(product);

            return saveProduct;
    }

    @Override
    public List<Category> getAllCategory() {

      return categoryRepository.findAll();
    }

    @Override
    public void deleteProduct(long id) throws ProductNotFoundException {

        if(productRepository.existsById(id)){
            productRepository.deleteById(id);
        }else{
            throw new ProductNotFoundException("Product not found with ID " + id);
        }

    }
    /*
    */
    @Override
    public Product updateProduct(long id, String desc, String title, double price, String image, String category) {

      Product product = productRepository.findByIdIs(id);

        if(product.getDescription()!=null){
            product.setDescription(desc);
        }
        if(product.getPrice()!=0.0d){
            product.setPrice(price);
        }

        if(product.getTitle()!=null){
            product.setTitle(title);
        }
        if(product.getImageURL() !=null){
            product.setImageURL(image);
        }

        Category categoryFromDatabase = categoryRepository.findByTitle(category);

        if(categoryFromDatabase==null){
            Category newcategory = new Category();
            newcategory.setTitle(category);
            categoryFromDatabase = newcategory;//no need to add category to DB as
            // cascade persist will create catego first
        }
        product.setCategory(categoryFromDatabase);

        Product saveProduct =   productRepository.save(product);

        return saveProduct;
    }

    @Override
    public List<Product> getAllCategoryWiseProduct(String jew) {
//        Category category = new Category();
//        category.setTitle(jew);
        //return productRepository.findByCategoryIs(jew);
//        Category category = categoryRepository.findByTitle(jew);
//        return productRepository.findByCategoryIs(category);

        return productRepository.findAllByCategory_Title(jew);
    }


}
