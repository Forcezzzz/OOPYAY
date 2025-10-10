package lab12_2;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		String Text = input.replaceAll("\\s", "");
		
		char operator ;
		String parts[];
        if (input.contains("+")) {
            operator = '+';
            parts = Text.split("\\+");
        } else if (input.contains("*")) {
            operator = '*';
            parts = Text.split("\\*");
        } else if (input.contains("-")) {
            operator = '-';
            parts = Text.split("\\-");
        } else return;
		
        Set<Integer> setA = parseSet(parts[0]);
        Set<Integer> setB = parseSet(parts[1]);
        Set<Integer> result = new TreeSet<>(setA);
        
        switch (operator)
        {
        	case '+' :
        		result.addAll(setB);
        		break;
        	case '*' :
        		result.retainAll(setB);
        		break;
        	case '-' :
        		result.removeAll(setB);
        		break;
        }
        
        System.out.println(result);
	}
	
	public static Set<Integer> parseSet(String str)
	{
		str = str.replace("[","").replace("]","");
		Set<Integer> set = new TreeSet<>();
		if (!str.isEmpty()) {
			for (String num : str.split(",")) {
				set.add(Integer.parseInt(num.trim()));
			}
		}
		return set;
	}
	

}