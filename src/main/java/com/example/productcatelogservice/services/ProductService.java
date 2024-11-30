package com.example.productcatelogservice.services;


import com.example.productcatelogservice.dtos.FakeStoreProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Override
    public List<Product> getProducts(){
        return null;
    }

    @Override
    public Product getProductById(Long id){
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoRestTemplate =  restTemplate.getForEntity("https://fakestoreapi.com/products/{id}", FakeStoreProductDto.class, id);

        if (fakeStoreProductDtoRestTemplate.getBody() == null || fakeStoreProductDtoRestTemplate.getStatusCode().equals(HttpStatusCode.valueOf(500))) {
            return null;
        }

        Product product = from(fakeStoreProductDtoRestTemplate.getBody());

        return product;

    }
    @Override
    public Product createProduct(Product product){
        return product;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto){
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());

        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());

        product.setCategory(category);

        return product;
    }
}
