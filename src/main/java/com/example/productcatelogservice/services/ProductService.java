package com.example.productcatelogservice.services;


import com.example.productcatelogservice.clients.FakeStoreApiClient;
import com.example.productcatelogservice.dtos.FakeStoreProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private FakeStoreApiClient fakeStoreApiClient;

    @Override
    public List<Product> getProducts(){
        FakeStoreProductDto[] fakeStoreProductDtos = fakeStoreApiClient.getProductById();

        if (fakeStoreProductDtos == null) {
            return null;
        }

        List<Product> products = new ArrayList<>();
        for(FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtos){
            products.add(from(fakeStoreProductDto));
        }

        return products;
    }

    @Override
    public Product getProductById(Long id){
        return from(fakeStoreApiClient.getProductById(id));

    }
    @Override
    public Product createProduct(Product product){
        return from(fakeStoreApiClient.createProduct(from(product)));
    }

    @Override
    public Product updateProduct(Long productId, Product product){
        return from(fakeStoreApiClient.updateProduct(productId, from(product)));
    }

    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    private FakeStoreProductDto from(Product product){
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setImage(product.getImageUrl());

        if (product.getCategory() == null) {
            return fakeStoreProductDto;
        }

        fakeStoreProductDto.setCategory(product.getCategory().getName());

        return fakeStoreProductDto;
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
