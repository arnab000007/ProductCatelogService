package com.example.productcatelogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;


@Entity(name = "mapped_mentor")
public class Mentor extends User {
    private Double rating;
}
