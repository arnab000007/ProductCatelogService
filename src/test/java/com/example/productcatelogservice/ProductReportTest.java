package com.example.productcatelogservice;

import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.models.State;
import com.example.productcatelogservice.repositories.ProductRepository;
import com.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class ProductReportTest {

    @Autowired
    private IProductService productService;

    @Test
    @Transactional
    public void testGetAllProducts(){
        List<Product> products = productService.getProducts();
        System.out.println("Total Size of : " +  products.size());
    }

    @Test
    @Transactional
    public void testGetActiveProduct(){
        Product product = productService.getProductById(2L);
        System.out.println("Product Name : " + product.getName());
    }

    @Test
    @Transactional
    public void testGetInActiveProduct(){
        Product product = productService.getProductById(1L);
        if (product == null) {
            System.out.println("Product is null");
            return;
        }
        else{
            AssertionError error = new AssertionError("Product is not null");
        }
    }
}
