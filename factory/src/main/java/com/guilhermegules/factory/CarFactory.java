package com.guilhermegules.factory;

import com.guilhermegules.model.Car;
import com.guilhermegules.model.DefaultCar;
import com.guilhermegules.model.Fiat;

public class CarFactory {

	public Car getCar(String name, String color, CarBrands carBrand) {
		switch (carBrand) {
		case FIAT:
			return new Fiat(name, color);
		default:
			return new DefaultCar();
		}
	}
}
