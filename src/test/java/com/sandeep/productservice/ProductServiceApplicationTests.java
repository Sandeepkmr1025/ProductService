package com.sandeep.productservice;

import com.sandeep.productservice.models.Category;
import com.sandeep.productservice.models.Product;
import com.sandeep.productservice.repositories.CategoryRepository;
import com.sandeep.productservice.repositories.ProductRepository;
import com.sandeep.productservice.repositories.projections.ProductWithIdAndTitle;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void contextLoads() {
    }

    @Test
    public void someRandomTest()
    {
       List<Product> products= productRepository.someRandomQuery();
       for (Product product: products) {
           System.out.println("Title :" + product.getTitle());
           System.out.println("Description :" + product.getDescription());
           System.out.println("Price :" + product.getPrice());
           System.out.println("Image :" + product.getImage());
       }
    }

    @Test
    public void someRandomTest2()
    {
        List<Product> products= productRepository.someRandomQuery2();
        for (Product product: products) {
            System.out.println("Title :" + product.getTitle());
            System.out.println("Description :" + product.getDescription());
            System.out.println("Price :" + product.getPrice());
            System.out.println("Image :" + product.getImage());
        }
    }

    @Test
    public void someRandomTest3()
    {
        List<ProductWithIdAndTitle> products= productRepository.someRandomQuery3();
        for (ProductWithIdAndTitle product: products) {
            System.out.println("Id :" + product.getId());
            System.out.println("Title :" + product.getTitle());
        }
    }

    @Test
    public void someRandomTest4()
    {
        List<ProductWithIdAndTitle> products= productRepository.someRandomQuery4(4L);
        for (ProductWithIdAndTitle product: products) {
            System.out.println("Id :" + product.getId());
            System.out.println("Title :" + product.getTitle());
        }
    }

    @Test
    public void someRandomTest5()
    {
        Product product = productRepository.someRandomQuery5();
        System.out.println("Id :" + product.getId());
        System.out.println("Title :" + product.getTitle());
    }

    @Test
    public void someRandomTest6()
    {
        categoryRepository.deleteById(152L);
    }

    @Test
    public void someRandomTest7()
    {
        productRepository.deleteById(102L);
    }

    @Test
    public void someRandomTest8()
    {
        Optional<Product> optionalProduct = productRepository.findById(1L);
        if (optionalProduct.isEmpty()) {
            return;
        }
        Product product = optionalProduct.get();
        System.out.println("DEBUG");
    }

    @Test
    public void someRandomTest9()
    {
        Optional<Category> optionalProduct = categoryRepository.findById(2L);
        if (optionalProduct.isEmpty()) {
            return;
        }
        Category category = optionalProduct.get();
        System.out.println("DEBUG");
    }

}
