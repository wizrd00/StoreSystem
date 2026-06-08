package core;

import core.csv.CSVReader;
import core.csv.CSVFileNotFound;

import java.util.ArrayList;
import java.util.HashMap;

public class Downloader {
	public String path;
	private CSVReader _csv_reader;

	public Downloader(String path)
		throws CSVFileNotFound
	{
		this.path = path;
		try {
			_csv_reader = new CSVReader(path);
		} catch (CSVFileNotFound err) {
			throw new CSVFileNotFound(err.getMessage());
		}
	}

	public ArrayList<Product> download()
		throws DownloadFailed, RuntimeException
	{
		ArrayList<Product> result = new ArrayList<>();
		ArrayList<HashMap<String, String>> list;
		try {
			if (!_csv_reader.is_parsed)
				_csv_reader.parseCSVFile();
		} catch (Exception err) {
			throw new DownloadFailed(err.getMessage());
		}
		list = _csv_reader.getContent();
		for (int i = 0; i < _csv_reader.getContentLength(); i++) {
			HashMap<String, String> item = list.get(i);
			Product product = new Product(item.get("name"), item.get("unit"), Float.parseFloat(item.get("price")), (long) i);
			if (product.price != 0.0f)
				product.setAvailable();
			product.setManufacturerName(item.get("manufacturer"));
			product.setColor(item.get("color"));
			product.setManufactureDate(item.get("manufacture_date"));
			product.setExpirationDate(item.get("expiration_date"));
			product.setWeight(Float.parseFloat(item.get("weight")));
			product.setVolume(Float.parseFloat(item.get("volume")));
			product.setDiscount(Integer.parseInt(item.get("discount")));
			result.add(product);
		}
		return result;
	}
}
