package com.example.productcatelogservice.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "joined_ta")
@PrimaryKeyJoinColumn(name = "user_id")
public class Ta extends User {
    private Long hours;
}
