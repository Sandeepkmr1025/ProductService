package com.sandeep.productservice.dtos;

import com.sandeep.productservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProductDto {

    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
