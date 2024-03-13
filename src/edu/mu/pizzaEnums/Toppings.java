package edu.mu.pizzaEnums;

import javax.swing.text.html.parser.Element;

public enum Toppings {
    TOMATO,
    CHEESE,
    BELL_PEPPER,
    BLACK_OLIVE,
    MUSHROOM,
    CANADIAN_BACON,
    PINEAPPLE,
    ITALIAN_SAUSAGE,
    PEPPERONI;
    
   public double getToppingPrice(Toppings topping) {
		switch (topping) {
			case TOMATO:
				return 1.50;
			case CHEESE:
				return 2.00;
			case PINEAPPLE:
				return 2.50;
			case BLACK_OLIVE:
				return 1.25;
			case ITALIAN_SAUSAGE:
				return 3.50;
			case PEPPERONI:
				return 3.00;
			case BELL_PEPPER:
				return 1.00;
			case MUSHROOM:
				return 1.50;
			case CANADIAN_BACON:
				return 4.00;
			default:
				return 0;
		}
	}
}
