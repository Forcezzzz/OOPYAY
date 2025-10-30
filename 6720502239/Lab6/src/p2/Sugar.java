package p2;

public class Sugar extends Product{
	protected int weight;
	
	public Sugar (double price) {
		super(price);
		super.name = "Sugar";
		super.unit = "Gram";
	}
	
}
