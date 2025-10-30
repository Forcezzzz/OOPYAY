package p1;

public class Graduate extends Student{
	protected String thesisTitle;
	
	public Graduate (double id, String name,double gpa,String thesisTitle) {
		super(id,name,gpa);
		this.thesisTitle = thesisTitle;
	}
	
	void setThesisTitle(String thesisTitle) {
		this.thesisTitle = thesisTitle;
	}
	
	public String getThesisTitle()	{
		return this.thesisTitle;
	}
	
	public String toString() {
		return "Graduate " + super.toString() + "\nThesis: " + this.thesisTitle;
	}
}
