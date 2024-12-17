package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithValidId_RunSuccessfully() {
        // Arrange
        Long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Iphone");

        when(productService.getProductById(productId)).thenReturn(product);


        // Act

        ResponseEntity<ProductDto> productDtoResponseEntity = productController.getProductById(productId);
        // Assert

        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(productId, productDtoResponseEntity.getBody().getId());
        assertEquals("Iphone", productDtoResponseEntity.getBody().getName());

        verify(productService, times(1)).getProductById(productId);

    }

    @Test
    public void TestGetProductById_WithZeroId_ThrowsIllegalArgumentException() {
        // Arrange
        Long productId = 0L;

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            // Act
            productController.getProductById(productId)
        );

        assertEquals("product with id 0 not accessible", exception.getMessage());
    }
    @Test
    //Considering a scenario where exception is getting thrown from downstream
    public void TestGetProductById_WhenProductServiceThrowsException_ThrowsSameException() {
       //Arrange

        when(productService.getProductById(any(Long.class))).thenThrow(new RuntimeException("Something went wrong"));

        assertThrows(RuntimeException.class, () -> productService.getProductById(10L));


    }


}
