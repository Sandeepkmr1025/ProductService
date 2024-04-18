package com.sandeep.productservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDto {
    private long id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
