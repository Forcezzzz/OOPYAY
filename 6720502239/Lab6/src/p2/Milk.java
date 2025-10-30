package p2;

public class Milk extends Product{

	protected int Volumn;
	
	public Milk(int price)	{
		super(price);
		super.name = "Milk";
		super.unit = "CC";
	}
	
	void setVolumn(int Volumn) {
		super.weight = Volumn;
	}
	
}
