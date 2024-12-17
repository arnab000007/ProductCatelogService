package com.example.productcatelogservice.TableInheritanceExamples.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "table_per_class_ta")
public class Ta extends User {
    private Long hours;
}
