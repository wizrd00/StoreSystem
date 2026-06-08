package core;

import java.util.ArrayList;

public class SetupStore {
	public String pull_path = "data/data.csv";
	public String push_path = "data/save.csv";
	private Downloader _downloader;
	private Uploader _uploader;

	public StoreSystem setup()
	{
		StoreSystem system;
		Store store = new Store();
		ArrayList<Product> products = null;
		try {
			_downloader = new Downloader(pull_path);
			_uploader = new Uploader(push_path);
			products = _downloader.download();
		} catch (Exception err) {
			System.out.printf("[!] fetal error : %s\nexiting...\n", err.getMessage());
			System.exit(1);
		}
		for (int i = 0; i < products.size(); i++)
			store.addProduct(products.get(i));
		system = new StoreSystem(store, _uploader);
		return system;
	}
}
