package com.example.productcatelogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.*;

@MappedSuperclass
public class User {
    @Id
    private Long id;
    private String email;
}
