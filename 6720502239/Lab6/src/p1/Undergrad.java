package p1;

public class Undergrad extends Student{
	protected String year;
	public Undergrad(double id, String name,double gpa,String year) {
		super(id,name,gpa);
		this.year = year;
	}
	
	void setYear(String year)	{
		this.year = year;
	}
	
	public String getYear()	{
		return this.year;
	}
	
	public String toString() {
		return "Undergraduate " + super.toString() + "\nYear: " + this.year;
	}
}
