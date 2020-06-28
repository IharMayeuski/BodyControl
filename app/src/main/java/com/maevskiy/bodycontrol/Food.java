package com.maevskiy.bodycontrol;

public class Food {
    private String name;
    private int calories;

    Food(String name, int calories) {
        this.calories = calories;
        this.name = name;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
