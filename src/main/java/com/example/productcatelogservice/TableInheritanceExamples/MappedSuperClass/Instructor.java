package com.example.productcatelogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "mapped_instructor")
public class Instructor extends User {
    private String company;
}
