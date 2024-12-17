package com.example.productcatelogservice.TableInheritanceExamples.JoinedClass;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@PrimaryKeyJoinColumn(name = "user_id")
@Entity(name = "joined_mentor")
public class Mentor extends User{
    private Double rating;
}
