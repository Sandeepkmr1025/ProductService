package com.sandeep.productservice.controllers;

import com.sandeep.productservice.commons.AuthenticationCommons;
import com.sandeep.productservice.dtos.Role;
import com.sandeep.productservice.dtos.UserDto;
import com.sandeep.productservice.exceptions.InvalidProductIdException;
import com.sandeep.productservice.exceptions.ProductControllerSpecificException;
import com.sandeep.productservice.models.Product;
import com.sandeep.productservice.services.IProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/products")
public class ProductController {

    private IProductService productService;
    private AuthenticationCommons authenticationCommons;
    private RestTemplate restTemplate;

    ProductController(@Qualifier("selfProductServiceImpl") IProductService productService, AuthenticationCommons authenticationCommons, RestTemplate restTemplate)
    {
        this.productService = productService;
        this.authenticationCommons = authenticationCommons;
        this.restTemplate = restTemplate;
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

        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://UserService/users/" + id, String.class);

          Product product = productService.getProductById(id);
          return new ResponseEntity<Product>(product, HttpStatus.OK);

    }

    //localhost:8080/products
//    @GetMapping("/all/{token}")
//    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token) {
//
//        UserDto userDto = authenticationCommons.validateToken(token);
//        if(userDto == null)
//        {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        List<Role> roles = userDto.getRoles();
//        //Stream<Role> admin = roles.stream().filter(role -> roles.equals("ADMIN"));
//        boolean isAdmin = false;
//        for(Role role : roles)
//        {
//            if(role.getName().equals("ADMIN"))
//            {
//                isAdmin = true;
//                break;
//            }
//        }
//        if(!isAdmin)
//        {
//            return null;
//        }
//        List<Product> products = productService.getAllProducts();
//        return new ResponseEntity<>(products, HttpStatus.OK);
//    }

    @GetMapping("/")
    public ResponseEntity<Page<Product>> getAllProducts(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize")int pageSize, @RequestParam("sortDir") String sortDir ) {
        Page<Product> products = productService.getAllProducts(pageNumber, pageSize, sortDir);
        return new ResponseEntity<>(products, HttpStatus.OK);
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
