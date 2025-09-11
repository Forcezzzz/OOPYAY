package p2;

public class SavingAccount extends BaseAccount{
	public class DebitCard extends Card {
		
		private SavingAccount saveAcc;
		
		public String type() {
			return "visa";
		}
		
		public double discount() {
			return 2.5;
		}
		
		public void setSaveAcc(SavingAccount saveAcc)
	    {
	        this.saveAcc = saveAcc;
	    }

	    public SavingAccount getSaveAcc()
	    {
	        return saveAcc;
	    }
	    
		public boolean withdraw(double amount) {
			return saveAcc.withdraw(amount);
		}
	}
	private Employee employee;
    private Card empCard = new DebitCard();
    private double balance;
	
    public SavingAccount(Employee employee) {
    	this.employee = employee;
        this.employee.setSavingEmpAcc(this);
        ((DebitCard)empCard).setSaveAcc(this);
	}
    
	public boolean deposit(double amount) {
		if (balance + amount >= 0) {
			balance = balance + amount;
			return true;
		}
		return false;
	}
	
	public boolean withdraw(double amount) {
		if (balance >= amount) {
			balance = balance - amount;
			return true;
		}
		return false;
	}
	
	public Card getCard()
    {
        return empCard;
    }

    public Employee getEmployees()
    {
        return employee;
    }
}
