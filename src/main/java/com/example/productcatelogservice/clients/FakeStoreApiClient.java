package com.example.productcatelogservice.clients;

import com.example.productcatelogservice.dtos.FakeStoreProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class FakeStoreApiClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductDto getProductById(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoRestTemplate = requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.GET, null, FakeStoreProductDto.class, id);
        return validateResponse(fakeStoreProductDtoRestTemplate);
    }

    public FakeStoreProductDto[] getProductById() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoRestTemplate = requestForEntity("https://fakestoreapi.com/products/", HttpMethod.GET, null, FakeStoreProductDto[].class, null);
        return validateResponseForList(fakeStoreProductDtoRestTemplate);
    }

    public FakeStoreProductDto updateProduct(Long productId, FakeStoreProductDto fakeStoreProductDto) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/{id}", HttpMethod.PUT, fakeStoreProductDto, FakeStoreProductDto.class, productId);
        return validateResponse(fakeStoreProductDtoResponseEntity);
    }
    public  FakeStoreProductDto createProduct(FakeStoreProductDto fakeStoreProductDto) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity = requestForEntity("https://fakestoreapi.com/products/", HttpMethod.POST, fakeStoreProductDto, FakeStoreProductDto.class, null);
        return validateResponse(fakeStoreProductDtoResponseEntity);
    }

    public <T> ResponseEntity<T> requestForEntity(String url, HttpMethod httpMethod, @Nullable Object request, Class<T> responseType, @Nullable Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor = restTemplate.responseEntityExtractor(responseType);
        if(uriVariables == null) {
            return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor);
        }

        return restTemplate.execute(url, httpMethod, requestCallback, responseExtractor, uriVariables);
    }

    public FakeStoreProductDto validateResponse(ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoResponseEntity) {
        if(fakeStoreProductDtoResponseEntity.getBody() == null ||
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(500))) {
            return null;
        }

        return fakeStoreProductDtoResponseEntity.getBody();
    }

    public FakeStoreProductDto[] validateResponseForList(ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoResponseEntity) {
        if(fakeStoreProductDtoResponseEntity.getBody() == null ||
                fakeStoreProductDtoResponseEntity.getStatusCode().equals(HttpStatusCode.valueOf(500))) {
            return null;
        }

        return fakeStoreProductDtoResponseEntity.getBody();
    }
}
