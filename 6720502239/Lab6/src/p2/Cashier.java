package p2;

public class Cashier {
	private String user;
	
	public Cashier (String user)	{
		this.user = user;
	}
			
	public void printReceipt(InventoryCart cart)	{
		
		Product [] item = cart.getAll(); //ดึงสินค้าจากรถออกมาให้หมด
		int total = 0;
		
		System.out.println("-----------------------------------\n");
		System.out.println("\tPumpkin Shop (" + user + ")\n");
		for (int i = 0 ; i < item.length ; i++)
		{
			System.out.println(item[i].getAmount() + " x " + item[i].getName() +"\t"+item[i].getWeight() + " " + item[i].getUnit() + "  \t" + (int)item[i].getPrice()*item[i].getAmount());
			total += item[i].getPrice()*item[i].getAmount();
		}
		
		System.out.println("-----------------------------------");
		System.out.println("\t\tTotal\t" + total + " $");
	}
}
