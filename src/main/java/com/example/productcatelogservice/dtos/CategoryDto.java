package com.example.productcatelogservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDto {
    private  Long id;
    private String name;
    private String description;
}
