package com.example.productcatelogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;


@Entity(name = "table_per_class_instructor")
public class Instructor extends User {
    private String company;
}
