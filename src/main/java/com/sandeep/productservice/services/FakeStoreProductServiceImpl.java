package com.sandeep.productservice.services;

import com.sandeep.productservice.dtos.FakeStoreProductDto;
import com.sandeep.productservice.dtos.ProductDto;
import com.sandeep.productservice.exceptions.InvalidProductIdException;
import com.sandeep.productservice.models.Category;
import com.sandeep.productservice.models.Product;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductServiceImpl")
public class FakeStoreProductServiceImpl implements IProductService{

    private RestTemplate restTemplate;

    public FakeStoreProductServiceImpl(RestTemplate restTemplate)
    {

        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) throws InvalidProductIdException {
        //Call the FakeStore API to get the product with the given ID here.
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null) {
            throw new InvalidProductIdException(id, "Invalid product id passed");
        }
        //Convert fakeStoreProductDto to product object
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {

        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setImage(fakeStoreProductDto.getImage());
        product.setPrice(fakeStoreProductDto.getPrice());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public Page<Product> getAllProducts(int pageNumber, int pageSize, String sortDir) {

       //List<FakeStoreProductDto> fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/", List.class);
        FakeStoreProductDto[] fakeStoreProductDtos = restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProductDto[].class);
        List<Product> products = new ArrayList<>();
        assert fakeStoreProductDtos != null;
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos)
        {
            products.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return new PageImpl<>(products);
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    /*@Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        //PUT Method
        //Replace the product with the given id with the input product
        //and return the updated product in the output.
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);
        assert fakeStoreProductDto != null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);

    }*/

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    /*@Override
    public Product updateProduct(Long id, ProductDto productDto) {

        //PATCH Method
        //Replace the product with the given id with the input product
        //and return the updated product in the output.
        RequestCallback requestCallback = restTemplate.httpEntityCallback(productDto, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor =
                new HttpMessageConverterExtractor<>(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PATCH, requestCallback, responseExtractor);
        assert fakeStoreProductDto != null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }*/

    @Override
    public void deleteProduct(Long id) {

    }

    @Override
    public String findTitleAndDescriptionById(Long id) {
        return null;
    }

}
