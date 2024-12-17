package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.dtos.CategoryDto;
import com.example.productcatelogservice.dtos.ProductDto;
import com.example.productcatelogservice.models.Category;
import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private IProductService productService;



    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<Product> products = productService.getProducts();

        if(products == null){
            return null;
        }

        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products) {
            productDtos.add(from(product));
        }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long id) {
        try {
            if(id < 0) {
                throw new IllegalArgumentException("productId is invalid");
            }
            else if(id == 0) {
                throw new IllegalArgumentException("product with id 0 not accessible");
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
            throw e;
        }
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = productService.createProduct(from(productDto));

        if ( product == null) {
            return null;
        }

        return from(product);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        try {
            Product inputProduct = from(productDto);
            if (id == null || id <= 0) {
                throw new IllegalArgumentException("Id cannot be null or less than or equal to 0");
            }

            Product updatedProduct = productService.updateProduct(id, inputProduct);
            if (updatedProduct == null) {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }

            return new ResponseEntity<>(from(updatedProduct), HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            throw e;
        }
    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        if (productDto.getCategory() != null) {
            Category category = new Category();
            category.setId(productDto.getCategory().getId());
            category.setDescription(productDto.getCategory().getDescription());
            category.setName(productDto.getCategory().getName());
            product.setCategory(category);
        }
        return product;
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
