package p1;

public class Student {
	protected String name;
	protected double id;
	protected double gpa;
	
	public Student(double id,String name,double gpa) {
		this.name = name;
		this.id = id;
		this.gpa = gpa;
	}
	public Student(double id,double gpa) {
		this.name = "";
		this.id = id;
		this.gpa = gpa;
	}
	
	String getName() {
		return this.name;
	}
	
	double getId()	{
		return this.id;
	}
	
	double getGPA()	{
		return this.gpa;
	}
	
	void setName(String name)	{
		this.name = name;
	}
	
	public String toString()	{
		return "Student:\nID: " + (int)this.id + "\nName: " + this.name + "\nGPA: "
				+ this.gpa;
	}
	
}
