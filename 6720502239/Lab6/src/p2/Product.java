package p2;

public class Product {
	protected double price;
	protected int weight;
	protected String name;
	protected String unit;
	protected int Amount = 1;
	
	public Product(double price)	{
		this.price = price;
	}

	void addAmount() {
		this.Amount++;
	}
	
	void setWeight(int weight) {
		this.weight = weight;
	}
	
	public double getPrice()	{
		return this.price;
	}
	
	public int getWeight() {
		return this.weight;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	void setUnit(String unit) {
		this.unit = unit;
	}
	
	public String getUnit() {
		return this.unit;
	}
	
	public int getAmount()	{
		return this.Amount;
	}
}
