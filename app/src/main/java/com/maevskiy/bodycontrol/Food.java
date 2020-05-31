package com.maevskiy.bodycontrol;

public enum Food {
    BEEF ("Beef", 180),
    CARROT_JUICE ("Carrot_juice", 70),
    CAVIAR ("Caviar", 250),
    CHICKEN ("Chicken", 150),
    DUCK ("Duck", 400),
    OAT_FLAKE ("Oat-flake", 70),
    PINK_SALMON ("Pink_salmon", 150),
    VEAL ("Veal", 200);

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
