package com.example.productcatelogservice.TableInheritanceExamples.MappedSuperClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "mapped_ta")
public class Ta extends User {
    private Long hours;
}
