package com.example.productcatelogservice.services;

import com.example.productcatelogservice.clients.FakeStoreApiClient;
import com.example.productcatelogservice.dtos.FakeStoreProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

@SpringBootTest
public class FakeStoreProductServiceTest {
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @MockBean
    private FakeStoreApiClient fakeStoreApiClient;

    @Autowired
    private FakeStoreProductService fakeStoreProductService;

    @Test
    public void TestGetProducts_RunSuccessFully(){
        FakeStoreProductDto fakeStoreProductDto1 = new FakeStoreProductDto();
        fakeStoreProductDto1.setId(1L);
        fakeStoreProductDto1.setTitle("Product 1");
        fakeStoreProductDto1.setCategory("Category 1");

        FakeStoreProductDto fakeStoreProductDto2 = new FakeStoreProductDto();
        fakeStoreProductDto2.setId(2L);
        fakeStoreProductDto2.setTitle("Product 2");
        fakeStoreProductDto2.setCategory("Category 2");


        when(fakeStoreApiClient.getProductById()).thenReturn(new FakeStoreProductDto[]{fakeStoreProductDto1, fakeStoreProductDto2});

        List<Product> products = fakeStoreProductService.getProducts();

        //Assert
        assertNotNull(products);
        assertEquals(2, products.size());
        assertEquals("Product 1", products.get(0).getName());
        assertEquals("Category 1", products.get(0).getCategory().getName());
        assertEquals("Product 2", products.get(1).getName());
        assertEquals("Category 2", products.get(1).getCategory().getName());

    }
    @Test
    public void TestGetProductsById_WithValidId_RunSuccessFully(){

        Long productId = 1L;
        FakeStoreProductDto fakeStoreProductDto1 = new FakeStoreProductDto();
        fakeStoreProductDto1.setId(productId);
        fakeStoreProductDto1.setTitle("Product 1");
        fakeStoreProductDto1.setCategory("Category 1");

        when(fakeStoreApiClient.getProductById(productId)).thenReturn(fakeStoreProductDto1);

        Product product = fakeStoreProductService.getProductById(productId);

        assertNotNull(product);
        assertEquals(productId, product.getId());
        assertEquals("Product 1", product.getName());
        assertEquals("Category 1", product.getCategory().getName());

    }

    @Test
    public void TestGetProductsById_WithInvalidId_RunSuccessFully(){

        Long productId = 1L;

        when(fakeStoreApiClient.getProductById(productId)).thenReturn(null);

        Product product = fakeStoreProductService.getProductById(productId);

        assertNull(product);
    }

    @Test
    public void TestCreateProduct_WithValidProduct_RunSuccessFully(){

        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");

        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(1L);
        fakeStoreProductDto.setTitle("Product 1");
        fakeStoreProductDto.setCategory("Category 1");

        when(fakeStoreApiClient.createProduct(any(FakeStoreProductDto.class))).thenReturn(fakeStoreProductDto);

        Product createdProduct = fakeStoreProductService.createProduct(product);

        assertNotNull(createdProduct);
        assertEquals(1L, createdProduct.getId());
        assertEquals("Product 1", createdProduct.getName());
        assertEquals("Category 1", createdProduct.getCategory().getName());
    }
}
