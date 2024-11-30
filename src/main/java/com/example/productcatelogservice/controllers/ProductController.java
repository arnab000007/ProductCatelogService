package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.dtos.CategoryDto;
import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;



    @GetMapping("/products")
    public List<ProductDto> getProducts(){
        return null;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        try {
            if (id == null || id == 0) {
                throw new IllegalArgumentException("Id cannot be null");
            }

            Product product = productService.getProductById(id);
            MultiValueMap<String, String> headers = new org.springframework.http.HttpHeaders();
            headers.add("Custom-Header", "Custom-Value");

            if (product == null) {
                return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            }

            ProductDto productDto = from(product);
            return new ResponseEntity<>(productDto, headers, HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return productDto;
    }


    private ProductDto from(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {

            CategoryDto category = new CategoryDto();
            category.setId(product.getCategory().getId());
            category.setDescription(product.getCategory().getDescription());
            category.setName(product.getCategory().getName());

            productDto.setCategory(category);
        }
        return productDto;
    }
}
