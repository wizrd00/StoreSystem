package test.TestReadCSV;

import core.csv.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVReaderTest {
	public static void main(String[] args) {
		CSVReader list = null;
		ArrayList<HashMap<String, String>> content = null;
		try {
			list = new CSVReader("./src/test/TestReadCSV/list.csv");
			list.parseCSVFile();
		} catch (Exception err) {
			System.out.println(err.getMessage());
			System.exit(1);
		}
		content = list.getContent();
		assert list.getContentLength() == 4;

		assert content.get(0).get("name").equals("milk");
		assert content.get(0).get("price").equals("10");
		assert content.get(0).get("unit").equals("liter");

		assert content.get(1).get("name").equals("egg");
		assert content.get(1).get("price").equals("1");
		assert content.get(1).get("unit").equals("pcs");

		assert content.get(2).get("name").equals("bread");
		assert content.get(2).get("price").equals("5");
		assert content.get(2).get("unit").equals("pcs");

		assert content.get(3).get("name").equals("rice");
		assert content.get(3).get("price").equals("12");
		assert content.get(3).get("unit").equals("kg");

	}
}
