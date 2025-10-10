package lab13;
import java.io.IOException;
import java.util.ArrayList;

public class main_readFile {

	public static void main(String[] args) throws IOException {
		ArrayList<item> listOnFile = new ArrayList<>();
		listOnFile =  ShopToText.ReadOnflie();
		ShopToText.getStatement(listOnFile);
	}

}
