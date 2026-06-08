package core;

import core.csv.CSVWriter;

import java.util.ArrayList;
import java.util.HashMap;

public class Uploader {
	public String path;
	public String[] keys = {
		"name",
		"unit",
		"price",
		"manufacturer",
		"color",
		"manufacture_date",
		"expiration_date",
		"weight",
		"volume",
		"discount"
	};
	private CSVWriter _csv_writer;

	public Uploader(String path)
	{
		this.path = path;
	}

	public void upload(ArrayList<Product> products)
		throws UploadFailed
	{
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		for (int i = 0; i < products.size(); i++) {
			HashMap<String, String> item = new HashMap<>();
			Product product = products.get(i);
			item.put("name", product.name);
			item.put("unit", product.unit);
			item.put("price", Float.toString(product.price));
			item.put("manufacturer", product.getManufacturerName());
			item.put("color", product.getColor());
			item.put("manufacture_date", product.getManufactureDate());
			item.put("expiration_date", product.getExpirationDate());
			item.put("weight", Float.toString(product.getWeight()));
			item.put("volume", Float.toString(product.getVolume()));
			item.put("discount", Integer.toString(product.getDiscount()));
			list.add(item);
		}
		try {
			_csv_writer = new CSVWriter(path, list, keys);
			_csv_writer.serializeCSVFile();
		} catch (Exception err) {
			throw new UploadFailed(err.getMessage());
		}
		_csv_writer = null;
	}
}
