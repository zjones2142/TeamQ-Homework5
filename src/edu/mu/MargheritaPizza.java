package edu.mu;

import java.util.*;

public class MargheritaPizza extends AbstractPizza{
	
	private static final double DEFAULT_PRICE = 2.50;
	
	public MargheritaPizza() {
		super();
		toppingList.add(Toppings.TOMATO);
		toppingList.add(Toppings.CHEESE);
		priceWithoutToppings = DEFAULT_PRICE;
	}

	//copy constructor
	public MargheritaPizza(MargheritaPizza pizza) {
		this();
		this.toppingList = new ArrayList<>(pizza.getToppingList());
		this.priceWithoutToppings = pizza.getPriceWithoutToppings();
		this.totalPrice = pizza.getTotalPrice();
		this.pizzaOrderID = pizza.getPizzaOrderID();
		this.cookingStrategy = pizza.getCookingStrategy();
		this.cookingPrice = pizza.getCookingPrice();
	}
	
	@Override
	protected double addToppingsToPrice(double priceWithoutToppings) {
		double total = priceWithoutToppings; //initial price without toppings
		for(Toppings topping : toppingList) {
			total += getToppingPrice(topping); //add the price for each topping
		}
		return total;
	}
	
	@Override
	public double updatePizzaPrice() {
		totalPrice = addToppingsToPrice(priceWithoutToppings);
		totalPrice += cookingPrice;
		return totalPrice;
	}
	
	private double getToppingPrice(Toppings topping) {
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
