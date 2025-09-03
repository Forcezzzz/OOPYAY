package p2;

public abstract class Employee {
	protected String firstName; 
	protected String lastName; 
	protected String securityNumber; 
	protected SavingAccount savingEmpAcc;
	
	public Employee(String first, String last, String ssn) { 
		firstName = first; 
		lastName = last; 
		securityNumber = ssn;
	} 
	
	public String toString() { 
	return String.format("%s %s\nsocial security number: %s",
			firstName, lastName, securityNumber); 
	} 
	
	public void setSavingEmpAcc(SavingAccount savingEmpAcc) {
        this.savingEmpAcc = savingEmpAcc;
    }
	
	public String getSSN()
    {
        return securityNumber;
    }
	
	public abstract double earnings(); 
	
}