package p2;

import java.util.Iterator;

public class main {
	public static void main(String[] args) {

		for (Iterator iter = ((Containers)() -> {
			String[] names = { "John", "May", "Ryan", "New", "Gun", "Thun", "Force" };

		
				return new Iterator() {
					int index = 0;

					
					public boolean hasNext() {
						if (index < names.length)
							return true;
						return false;
					}

					public Object next() {
						return names[index++];
					}

					public void remove() {

					}
				};
			
		}).getIterator();iter.hasNext();) {
			String name = (String) iter.next();
			System.out.println("Name : " + name);
		}
	}
}
