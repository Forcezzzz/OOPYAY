package p3;

public class HourlySalary extends Employee{
	protected double hourlyPay;
	protected double hourlyWork;
	
	public HourlySalary(String first,String last,String ssn,double hourlyPay,double hourlyWork) {
		super(first,last,ssn);
		this.hourlyPay = hourlyPay;
		this.hourlyWork = hourlyWork;
	}
	
	public double earnings() {
		return hourlyPay*hourlyWork;
	}
	
	public String toString() {
		return "hourly salary employee: " + super.toString() + "\nhourly pay: $" +
				this.hourlyPay + "  hours worked: " + this.hourlyWork ;
	}
	
}
