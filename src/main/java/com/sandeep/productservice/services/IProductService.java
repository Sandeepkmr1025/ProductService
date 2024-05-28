package com.sandeep.productservice.services;

import com.sandeep.productservice.exceptions.InvalidProductIdException;
import com.sandeep.productservice.models.Product;
import org.springframework.data.domain.Page;

public interface IProductService {

    Product getProductById(Long id) throws InvalidProductIdException;

    Page<Product> getAllProducts(int pageNumber, int pageSize, String sortDir);

    Product createProduct(Product product);

    //Product replaceProduct(Long id, ProductDto productDto);

    Product replaceProduct(Long id, Product product);

    //Product updateProduct(Long id, ProductDto productDto);

    Product updateProduct(Long id, Product product);

    void deleteProduct(Long id);

    String findTitleAndDescriptionById(Long id);
}
