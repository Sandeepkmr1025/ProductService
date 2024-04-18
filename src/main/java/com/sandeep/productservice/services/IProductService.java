package com.sandeep.productservice.services;

import com.sandeep.productservice.dtos.ProductDto;
import com.sandeep.productservice.exceptions.InvalidProductIdException;
import com.sandeep.productservice.models.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProductService {

    Product getProductById(Long id) throws InvalidProductIdException;

    List<Product> getAllProducts();

    Product createProduct(Product product);

    //Product replaceProduct(Long id, ProductDto productDto);

    Product replaceProduct(Long id, Product product);

    //Product updateProduct(Long id, ProductDto productDto);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    String findTitleAndDescriptionById(Long id);
}
