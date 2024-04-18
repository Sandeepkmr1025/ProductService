package com.sandeep.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel{

    @OneToMany(fetch = FetchType.EAGER,mappedBy = "category",cascade = CascadeType.REMOVE)
    private List<Product> products;
    private String title;
}
