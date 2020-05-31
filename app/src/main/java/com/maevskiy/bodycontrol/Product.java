package com.maevskiy.bodycontrol;

public class Product {
    private String name;
    private int weight;
    private int calories;

    Product(String name, int weight, int calories) {
        this.name = name;
        this.weight = weight;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getCalories() {
        return calories;
    }

    @Override
    public String toString() {
        return name + " (" + calories + " cal.) - " + weight + " (g.)";
    }
}

