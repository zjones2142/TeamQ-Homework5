package edu.mu;

import java.util.*;
import edu.mu.pizzaEnums.*;

public class PizzaOrder {
	
	private PizzaCookingFactory pizzaFactory;
	private ICookingStrategy cookingStrategy;
	private List<AbstractPizza> pizzaOrderList; 

	public PizzaOrder() {
		
	}
	
	public void printListOfToppingsByPizzaOrderID(int orderID) {
		
	}
	
	public void printPizzaOrderCart(int orderID) {
		
	}
	
	public AbstractPizza getPizzaByOrderID(int orderID) {
		return null;
	}
	
	public boolean addPizzaToCart(PizzaType pizzaType) {
		return false;
	}
	
	public boolean addNewToppingToPizza(int orderID, Toppings topping) {
		return false;
	}
	
	public boolean removeToppingFromPizza(int orderID, Toppings topping) {
		return false;
	}
	
	public boolean isThereAnyUncookedPizza() {
		return false;
		
	}
	
	public double checkout() throws Exception {
		return 0;
	}
	
	public boolean selectCookingStrategyByPizzaOrderID(int orderID, CookingStyleType cookingStrategyType) {
		return false;
	}
}
