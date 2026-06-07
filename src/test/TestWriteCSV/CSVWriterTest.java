package test.TestWriteCSV;

import core.csv.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVWriterTest {
	public static void main(String[] args) {
		CSVWriter list = null;
		String[] keys = {"name", "price", "unit"};
		ArrayList<HashMap<String, String>> content = new ArrayList<>();

		HashMap<String, String> item0 = new HashMap<>();
		item0.put("name", "milk");
		item0.put("price", "10");
		item0.put("unit", "liter");

		HashMap<String, String> item1 = new HashMap<>();
		item1.put("name", "egg");
		item1.put("price", "1");
		item1.put("unit", "pcs");

		HashMap<String, String> item2 = new HashMap<>();
		item2.put("name", "bread");
		item2.put("price", "5");
		item2.put("unit", "pcs");

		HashMap<String, String> item3 = new HashMap<>();
		item3.put("name", "rice");
		item3.put("price", "12");
		item3.put("unit", "kg");

		content.add(item0);
		content.add(item1);
		content.add(item2);
		content.add(item3);
		try {
			list = new CSVWriter("./test/TestWriteCSV/list.csv", content, keys);
			list.serializeCSVFile();
		} catch (Exception err) {
			System.out.println(err.getMessage());
			System.exit(1);
		}
	}
}
