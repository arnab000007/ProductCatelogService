package com.example.productcatelogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@DiscriminatorValue(value = "2")
@Entity(name = "single_mentor")
public class Mentor extends User {
    private Double rating;
}
