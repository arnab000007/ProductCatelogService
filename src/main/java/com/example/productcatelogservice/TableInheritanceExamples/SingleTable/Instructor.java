package com.example.productcatelogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "single_instructor")
@DiscriminatorValue(value = "1")
public class Instructor extends User {
    private String company;
}
