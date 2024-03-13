package edu.mu;

import java.util.*;
import edu.mu.pizzaEnums.*;

public class PizzaOrder {
	
	private PizzaCookingFactory pizzaFactory;
	private ICookingStrategy cookingStrategy;
	private List<AbstractPizza> pizzaOrderList; 

	public PizzaOrder() {
		this.pizzaFactory = new PizzaCookingFactory();
		this.pizzaOrderList = new ArrayList<>();
	}
	
	public void printListOfToppingsByPizzaOrderID(int orderID) {
		AbstractPizza pizza = getPizzaByOrderID(orderID);
		if(pizza != null){
			System.out.println("Toppings for pizza " + orderID + ":");
			for(Toppings topping : pizza.getToppingList()){
				System.out.println(topping);
			}
		} else {
			System.out.println("Pizza order " + orderID + " not found");
		}
	}
	
	public void printPizzaOrderCart(int orderID) {
		System.out.println("Pizza Order List:");
		for(AbstractPizza pizza : pizzaOrderList){
			System.out.println(pizza.toString());
		}
	}
	
	public AbstractPizza getPizzaByOrderID(int orderID) {
		return null;
	}
	
	public boolean addPizzaToCart(PizzaType pizzaType) {
		PizzaCookingFactory pizza = new PizzaCookingFactory();
		AbstractPizza pizza1 = pizza.createPizza(pizzaType);
		pizzaOrderList.add(pizza1);
		return true;
	}
	
	public boolean addNewToppingToPizza(int orderID, Toppings topping) {
		return false;
	}
	
	public boolean removeToppingFromPizza(int orderID, Toppings topping) {
		return false;
	}
	
	public boolean isThereAnyUncookedPizza() {
		for (AbstractPizza pizza : pizzaOrderList) {
            if (pizza.getCookingStrategy() == null) {
                return true;
            }
        }
        return false;
	}
	
	public double checkout() throws Exception {
		if (isThereAnyUncookedPizza()) {
            throw new Exception("There are uncooked pizzas in your order.");
        }
        double total = 0;
        for (AbstractPizza pizza : pizzaOrderList) {
            total += pizza.getTotalPrice();
        }
        return total;
	}
	
	public boolean selectCookingStrategyByPizzaOrderID(int orderID, CookingStyleType cookingStrategyType) {
		for (AbstractPizza pizza : this.pizzaOrderList) {
			if(pizza.getPizzaOrderID() == orderID) {
				this.cookingStrategy = pizza.getCookingStrategy();
				this.cookingStrategy.cook(pizza);
				return true;
			}
		}
		return false;
	}
}
