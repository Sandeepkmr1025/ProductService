package com.sandeep.productservice.services;

import com.sandeep.productservice.exceptions.InvalidProductIdException;
import com.sandeep.productservice.models.Product;
import com.sandeep.productservice.repositories.CategoryRepository;
import com.sandeep.productservice.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductServiceImpl")
//@Primary
public class SelfProductServiceImpl implements IProductService{

    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    SelfProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository)
    {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        //Fetch Product with given id from DB.
        Optional<Product> optionalProduct = productRepository.findById(id);
        //throw an exception ProductNotFound
        return optionalProduct.orElse(null);
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortDir) {
        return productRepository.findAll(PageRequest.of(pageNumber,
                pageSize,
                sortDir.equals("asc") ? Sort.by("price").ascending() :
                        Sort.by("price").descending()));
    }

    @Override
    public Product createProduct(Product product) {

       /* Category category = product.getCategory();
        if (category.getId()==null)
        {
            Category savedCategory = categoryRepository.save(category);
            product.setCategory(savedCategory);
        }
       Category savedCategory = categoryRepository.save(category);
        product.setCategory(savedCategory);*/
       return productRepository.save(product);
    }

    //TODO
    @Override
    public Product replaceProduct(Long id, Product product) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isEmpty())
        {
            throw new RuntimeException("Product does not exist for the given id: "+id);
        }
        if(product == null)
        {
            throw new RuntimeException("Invalid input exception to the replace method");
        }
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty())
            throw new RuntimeException("Product does not exist for the given id");
        if (product == null)
            throw new RuntimeException("Invalid input exception to update method");

        Product currentProduct = optionalProduct.get();

        if(product.getTitle() != null)
        {
            //If title is not null that means we want to update the title.
            currentProduct.setTitle(product.getTitle());
        }

        if(product.getDescription() != null)
        {
            //If title is not null that means we want to update the title.
            currentProduct.setDescription(product.getDescription());
        }

        return productRepository.save(currentProduct);
    }

    //TODO
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public String findTitleAndDescriptionById(Long id) {
        return productRepository.findTitleAndDescriptionById(id);
    }
}
