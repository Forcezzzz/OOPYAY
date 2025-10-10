package lab13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ShopToText {
	public static void SaveOnfile(ArrayList<item> mylist) throws IOException {
		FileWriter fw = new FileWriter("sell.txt",true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(item items : mylist){
			bw.write(items.name + " " + items.price + " " + items.quantity + "\n");
		}
		
		bw.close();
		fw.close();
	    System.out.println("Done: Save to sell.txt ");

	}
	public static ArrayList<item> ReadOnflie() throws IOException{
		// file -> objects(item)
		FileReader fr = new FileReader("sell.txt");
		BufferedReader br = new BufferedReader(fr);
		
		String name;
		double price;
		int quantity;
		
		ArrayList<item> listOnFlie = new ArrayList<>();
		String line;
		while((line = br.readLine()) !=  null) {
			String parts[] = line.split(" ");
			name = parts[0];
			price = Double.parseDouble(parts[1]);
			quantity = Integer.parseInt(parts[2]);	
			item items = new item(name, price, quantity);
			listOnFlie.add(items);
		}
		
		br.close();
		fr.close();
	    System.out.print("Done: Read from sell.txt \nList : " + listOnFlie.size() + " items");
	    System.out.println();

        return listOnFlie;
	}
	public static void getStatement(ArrayList<item> listOnFile) throws IOException{
		//แสดงรายการสินค้าไม่ให้ซ้ำ
		Set<String> productName = new TreeSet<>();
		for(item items : listOnFile) {
			productName.add(items.name);
		}
		int count = 1 ;
		//set ไม่สามารถอ้างอิงด้วย index เลยต้อง แปลงจาก Set -> ArrayList
		ArrayList<String> names = new ArrayList<>(productName);
		for(String name : names) {
			System.out.println(count + ".\t" + name);
			count++;
		}
		
		// เลือก
		System.out.print("Choose one Products that like to see statement : ");
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
        String selectedName = names.get(choice - 1);
		
        //calculate 
        double totalSale = 0;
        int totalQlity = 0;
        double priceProduct = 0;
        for(item items : listOnFile){
        	if(items.name.equals(selectedName)) {
        		priceProduct = items.price;
        		totalQlity = items.quantity;
        		totalSale = totalQlity * items.price;
        	}
        }
        
        System.out.println("\n===== Product Summary =====");
        System.out.println("Product : " + selectedName);
        System.out.printf("Price per unit : %.2f Baht\n", priceProduct);
        System.out.println("Total Quantity : " + totalQlity);
        System.out.printf("Total Sale : %.2f Baht\n", totalSale);
        System.out.println("============================");

        }

}
