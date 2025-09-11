package p2;

import p2.SavingAccount.DebitCard;

public class Cashier {
	private double total;
    private double[] unit;
    private double[] showUnit;
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
			System.out.println("\t" + this.cart[i] + " x " + productNames[i] + "  \t" + showUnit[i]);
		}
		
		System.out.println("\t--------------------\n");
		System.out.println("\tCARD DISCOUNT " + cardDiscount + " %");
		System.out.println("\tTotal\t" + total );
		
	}
	
	public void doPayment(InventoryCart cart , Card card) {
		total = 0;
		//System.out.printf(">>%d\n",cart.getIndex()); // getIndex ไม่ใช่จนสินค้าทั้งหมดในรถเข็น แต่เป็นจนสินค้าโดยไม่นับอันที่ซ้ำไปแล้ว(ไปดูใน.add Invertory) ทำให้ทำงานไม่ครบรอบ
		//หรือถ้าอยากใช้แบบเดิม
		//System.out.printf(">>%d\n",cart.getAll().length); //ใช้อันนี้แทน แล้วเอาอันที่เพิ่มจำนวนไม่เพิ่มสินค้าในคลาสInventoryออก
		productDifference = 0;
		Product[] p = cart.getAll();
        int[] a = new int[cart.getAll().length];

        unit = new double[cart.getAll().length];
        showUnit = new double[cart.getAll().length];
        productNames = new String[cart.getAll().length];
        this.cart = new int[cart.getAll().length];

        for (int i = 0 ; i < cart.getAll().length; i++)
        {
        	if (a[i] == 1) 
        		continue;
//            p[i].Amount = 1;  //?? ใส่ไว้ทำไมม
            unit[productDifference] = p[i].getPrice();
            showUnit[productDifference] = p[i].getPrice();
//            System.out.printf("%s++%.2f\n",p[i].getName(),p[i].getPrice());
            productNames[productDifference] = p[i].getName();
            
            for (int j = i + 1 ; j < cart.getAll().length ; j++)
            {
//            	System.out.printf("%s cmp with %s \n",p[i].getName(),p[j].getName());
                if ( p[i].getName().equals(p[j].getName()) ) // แล้วถ้าชื่อเหมือน ราคาไม่เท่ากัน ก็นับเป็นอันเดียวกันอ่ะดิ
                {
                    p[i].addAmount(); // ?? ที่ทำอยู่ตอนนี้คือถ้าวัตถุที่มีชื่อเหใือนกันจะสรุปไปเลยว่าตัวเดียวกัน
                    unit[productDifference] += p[j].getPrice();
                    //System.out.printf("+%f\n",p[j].getPrice());
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