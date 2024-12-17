package com.example.productcatelogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;


@Entity(name = "table_per_class_mentor")
public class Mentor extends User {
    private Double rating;
}
