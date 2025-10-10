package lab13;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class main_item {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		ArrayList<item> mylist = new ArrayList<>();
		
		String name;
		double price;
		int quantity;
		String status = "y";
		
		while(status.equals("y")) {
			System.out.printf("Enter the name of the item: ");
			name = sc.nextLine();
			System.out.printf("Enter the unit price: ");
			price = sc.nextDouble();
			System.out.printf("Enter the quantity: ");
			quantity = sc.nextInt();
			sc.nextLine();
			item items = new item(name, price, quantity);
			mylist.add(items);
			
			System.out.printf("Continue shopping (y/n)?: ");
			status = sc.nextLine();

		}
		ShopToText.SaveOnfile(mylist);
		
		System.out.println("Final Shopping Cart totals");
		double amount = 0;
		double total_amount = 0;
		for(item items : mylist) {
			amount = items.price*items.quantity;
			total_amount += amount;
			
			System.out.println(items.name + "\t$" + items.price + "\t" + items.quantity + "\t$" + amount);
		}
		System.out.printf("Total $ Amount in Cart: %.2f", total_amount);
		
//		ArrayList<item> listOnFile = new ArrayList<>();
//		listOnFile =  ShopToText.ReadOnflie();
//		ShopToText.getStatement(listOnFile);
		
	}

}
