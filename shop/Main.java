package shop;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		boolean isCon = true;
		String con;
		String name;
		double price;
		int quantity;
		double sum=0;
		Scanner sc = new Scanner(System.in);
		ArrayList<Item> myList = new ArrayList<>();
		while (isCon) {
			System.out.println("Enter the name of the item: ");
			name = sc.nextLine();
			System.out.println("Enter the unit price: ");
			price = sc.nextDouble();
			System.out.println("Enter the quantity: ");
			quantity = sc.nextInt();
			sc.nextLine();
			sum += price*quantity;
			Item item = new Item(name,price,quantity);
			myList.add(item);
			System.out.println("Continue shopping (y/n)? ");
			con = sc.nextLine();
			if (con.equals("n"))isCon = false;
				
		}
		System.out.println("Final Shopping Cart totals");
		for (Item item:myList) {
			System.out.println(item.name + "\t$" + item.price + "\t" + item.quantity + "\t$" + item.price*item.quantity);
		}
		System.out.println("Total $ Amount in Cart: " + sum);
	}
	
}
