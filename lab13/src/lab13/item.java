package lab13;

import java.io.Serializable;

public class item implements Serializable{
	String name;
	double price;
	int quantity;
	
	public item (String name, double price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
}
