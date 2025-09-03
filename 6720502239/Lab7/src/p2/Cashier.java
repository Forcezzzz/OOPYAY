package p2;

import p2.SavingAccount.DebitCard;

public class Cashier {
	private double total;
    private double[] unit;
    private int[] cart;
    private String[] productNames;
    private int productDifference;
    private double cardDiscount;
	
    public static void cartCheck(Product[] p1) {
        for(Product n: p1) {
            if(n==null){
                continue;
            }
            System.out.print(n.getName() + " ");
        }
        System.out.println();
    }
    
	public void printReceipt()	{
		
		System.out.println("\tPumpkin Shop\n");
		for (int i = 0 ; i < productDifference ; i++)
		{
			System.out.println("\t" + this.cart[i] + " x " + productNames[i] + "  \t" + unit[i]);
		}
		
		System.out.println("\t--------------------\n");
		System.out.println("\tCARD DISCOUNT " + cardDiscount + " %");
		System.out.println("\tTotal\t" + total );
		
	}
	
	public void doPayment(InventoryCart cart , Card card) {
		total = 0;
		productDifference = 0;
		Product[] p = cart.getAll();
        int[] a = new int[cart.getIndex()];

        unit = new double[cart.getIndex()];
        productNames = new String[cart.getIndex()];
        this.cart = new int[cart.getIndex()];

        for (int i = 0 ; i < cart.getIndex(); i++)
        {
        	if (a[i] == 1) 
        		continue;
            p[i].Amount = 1;
            unit[productDifference] = p[i].getPrice();
            productNames[productDifference] = p[i].getName();
            
            for (int j = i + 1 ; j < cart.getIndex() ; j++)
            {
                if ( p[i].getName().equals(p[j].getName()) )
                {
                    p[i].addAmount();
                    unit[productDifference] += p[j].getPrice();
                    a[j] = 1;
                }
            }
            this.cart[productDifference] = p[i].getAmount();
            total += unit[productDifference];
            productDifference++;

        }

        System.out.println("\tCARD TYPE:" + card.type());

        String cardNumber = ((DebitCard)card).getSaveAcc().getEmployees().getSSN();
        cardNumber = cardNumber.substring(0, cardNumber.length() - 4).replaceAll("[0-9]", "X") + cardNumber.substring(cardNumber.length() - 4); 
        System.out.println("\tCARD NUMBER:" + cardNumber);

        cardDiscount = card.discount();
        total = total - total*(card.discount()/100);
        card.withdraw(total);

	}
}