package com.guilhermegules.view;

import com.guilhermegules.factory.CarBrands;
import com.guilhermegules.factory.CarFactory;

public class Main {

		public static void main(String[] args) {
			CarFactory carFactory = new CarFactory();
			
			String name = "Uno";
			String color = "white";
			
			carFactory.getCar(name, color, CarBrands.FIAT);
		}
}
