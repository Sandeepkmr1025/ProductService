package com.sandeep.productservice.controllers;

import com.sandeep.productservice.exceptions.InvalidProductIdException;
import com.sandeep.productservice.exceptions.ProductControllerSpecificException;
import com.sandeep.productservice.models.Product;
import com.sandeep.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;

    ProductController(@Qualifier("selfProductServiceImpl") IProductService productService)
    {

        this.productService = productService;
    }

    //localhost:8080/products/10
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws InvalidProductIdException {
//        Product product = null;
//        try {
//             product = productService.getProductById(id);
//        } catch (RuntimeException e) {
//            System.out.println("Something went wrong");
//            return new ResponseEntity<>(product, HttpStatus.NOT_FOUND);
//        }
          Product product = productService.getProductById(id);
          return new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    //localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    //Create a product
    @PostMapping
    public Product createProduct(@RequestBody Product product)
    {
       return productService.createProduct(product);
    }

    //Partial update
    /*@PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto)
    {
        return productService.updateProduct(id, productDto);
    }*/

    //Partial update
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product product)
    {
        return productService.updateProduct(id, product);
    }

    //Replace the product
    /*@PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto )
    {
        return productService.replaceProduct(id, productDto);
    }*/

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody Product product )
    {
        return productService.replaceProduct(id, product);
    }

    //Replace
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable("id") Long id)
    {
        productService.deleteProduct(id);
    }

    //Custom query
   // @GetMapping("/{id}")
    public String findTitleAndDescriptionById(@PathVariable("id") Long id)
    {
        return productService.findTitleAndDescriptionById(id);
    }

    @ExceptionHandler(ProductControllerSpecificException.class)
    public ResponseEntity<Void> handleProductControllerSpecificException()
    {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
