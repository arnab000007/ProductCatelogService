package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;

import java.util.List;

public interface IProductService {
    List<Product> getProducts();

    Product getProductById(Long id);

    Product createProduct(Product product);
    Product updateProduct(Long productId, Product product);
}
