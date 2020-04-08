package com.guilhermegules.model;

public class Fiat extends Car {

	public Fiat(String name, String color) {
		setName(name);
		setColor(color);
		System.out.println("This is a Fiat car with name: " + name + " and color: " + color);
	}
}
