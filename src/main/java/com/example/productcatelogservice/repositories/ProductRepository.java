package com.example.productcatelogservice.repositories;

import com.example.productcatelogservice.models.Product;
import com.example.productcatelogservice.models.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findByIdAndState(Long productId, State state);

    List<Product> findAllByState(State state);

    Product save(Product product);
}
