package com.maevskiy.bodycontrol;

public enum Food {
    BROCCOLI ("broccoli", 2900, "Useful product"),
    BREAD ("bread", 2900, "Try to eat not often"),
    APPLE ("apple", 1500, "Eat every time when do you wand");

    private String name;
    private int calories;
    private String text;

    Food(String name, int calories, String text) {
        this.calories = calories;
        this.name = name;
        this.text = text;
    }

    public int getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return name;
    }
}
