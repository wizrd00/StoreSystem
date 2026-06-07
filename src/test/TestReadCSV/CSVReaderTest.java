package test.TestReadCSV;

import core.csv.*;

import java.util.ArrayList;
import java.util.HashMap;

public class CSVReaderTest {
	public static void main(String[] args) {
		CSVReader list = null;
		ArrayList<HashMap<String, String>> content = null;
		try {
			list = new CSVReader("./test/TestReadCSV/list.csv");
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
		System.out.printf("[0] -> %s, %s, %s\n", content.get(0).get("name"), content.get(0).get("price"), content.get(0).get("unit"));

		assert content.get(1).get("name").equals("milk");
		assert content.get(1).get("price").equals("10");
		assert content.get(1).get("unit").equals("liter");
		System.out.printf("[1] -> %s, %s, %s\n", content.get(1).get("name"), content.get(1).get("price"), content.get(1).get("unit"));

		assert content.get(2).get("name").equals("milk");
		assert content.get(2).get("price").equals("10");
		assert content.get(2).get("unit").equals("liter");
		System.out.printf("[2] -> %s, %s, %s\n", content.get(2).get("name"), content.get(2).get("price"), content.get(2).get("unit"));

		assert content.get(3).get("name").equals("milk");
		assert content.get(3).get("price").equals("10");
		assert content.get(3).get("unit").equals("liter");
		System.out.printf("[3] -> %s, %s, %s\n", content.get(3).get("name"), content.get(3).get("price"), content.get(3).get("unit"));

	}
}
