package com.maevskiy.bodycontrol;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {
    private int id;
    private String name;
    private int weight;
    private double calories;
}

