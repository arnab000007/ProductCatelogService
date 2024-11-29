package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.dtos.ProductDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        return null;
    }

    @GetMapping("/products/{id}")
    public ProductDto getProductById(@PathVariable("id") Long id){
        ProductDto productDto = new ProductDto();
        productDto.setId(id);
        productDto.setName("Product Name");

        return  productDto;

    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productDto;
    }
}
