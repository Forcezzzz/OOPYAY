package p3;

public class FixedSalary extends Employee{
	protected double salary;
	
	public FixedSalary(String first,String last,String ssn,double salary) {
		super(first,last,ssn);
		this.salary = salary;
	}
	
	public double earnings() {
		return salary;
	}
	
	public String toString() {
		return "Fixed salary employee: " + super.toString() + "\nmonthly salary: $" +
				this.salary ;
	}
}
