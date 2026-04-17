package org.example;

public class Car {
    private String name;
    private String color;

    public Car(String name, String color) {
        this.name = name;
        this.color = color;
    }

    // Needed for the example of the dynamic constructor
    public Car() {
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}
