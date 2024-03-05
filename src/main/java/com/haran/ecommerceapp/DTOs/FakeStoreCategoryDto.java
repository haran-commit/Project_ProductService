package com.haran.ecommerceapp.DTOs;

import com.haran.ecommerceapp.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreCategoryDto {
    private long id;
    private String category;

    public Category tocategory(){

        Category category1 = new Category();

        category1.setTitle(category);
        category1.setId(id);

        return category1;
    }

}
