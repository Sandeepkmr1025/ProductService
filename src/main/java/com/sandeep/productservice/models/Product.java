package com.sandeep.productservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel{
//    @Id
//    private long id; --> Moved to BaseModel class.
    private String title;
    private double price;
   // @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category; // EAGER FETCH
    private String description;
    private String image;

}
