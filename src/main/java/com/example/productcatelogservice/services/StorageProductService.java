package com.example.productcatelogservice.services;

import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.models.State;
import com.example.productcatelogservice.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

//@Primary
@Service("sps")
public class StorageProductService  implements  IProductService{

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAllByState(State.ACTIVE);
    }

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepository.findByIdAndState(id, State.ACTIVE);

        return productOptional.orElse(null);

    }

    @Override
    public Product createProduct(Product product) {

        product.setState(State.ACTIVE);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Long productId, Product product) {

        Optional<Product> productOptional = productRepository.findById(productId);
        if(productOptional.isEmpty()){
            return null;
        }

        Product toBeUpdated = updateProductObj(productOptional.get(), product);
        return productRepository.save(product);
    }

    public Product updateProductObj(Product toBeUpdated, Product newProduct) {

        toBeUpdated.setName(newProduct.getName());
        toBeUpdated.setPrice(newProduct.getPrice());
        toBeUpdated.setCategory(newProduct.getCategory());
        toBeUpdated.setImageUrl(newProduct.getImageUrl());
        toBeUpdated.setIsPrime(newProduct.getIsPrime());

        if (newProduct.getCategory() != null) {

            toBeUpdated.setCategory(newProduct.getCategory());

        }
        else{
            toBeUpdated.setCategory(null);
        }

        return productRepository.save(toBeUpdated);
    }
}
