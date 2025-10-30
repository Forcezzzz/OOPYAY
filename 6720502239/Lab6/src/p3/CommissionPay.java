package p3;

public class CommissionPay extends Employee{
	protected double sales;
	protected double rate;
	
	public CommissionPay(String first,String last,String ssn,double sales,double rate) {
		super(first,last,ssn);
		this.sales = sales;
		this.rate = rate;
	}
	
	public double earnings() {
		return sales*rate;
	}
	
	public String toString() {
		return "Commission employee: " + super.toString() + "\ngross sales: $" +
				this.sales + "  commission rate: " + this.rate;
	}
}
