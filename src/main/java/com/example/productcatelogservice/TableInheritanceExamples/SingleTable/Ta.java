package com.example.productcatelogservice.TableInheritanceExamples.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "single_ta")
@DiscriminatorValue(value = "3")
public class Ta extends User {
    private Long hours;
}
