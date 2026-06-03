package core;

public class StoreSystem {
	private ShoppingChart _chart = new ShoppingChart();
	private Invoice _invoice = new Invoice();
	private Store _store;
	private Uploader _upload;

	public StoreSystem(Store store, Uploader upload)
	{
		this._store = store;
		this._upload = upload;
	}

	public Product[] searchProductByName(String name)
		throws ProductNotFound
	{
		Product[] result;
		try {
			ArrayList<Product> products = _store.searchByName(name);
			result = products.toArray(new Product[products.size()])
		} catch (ProductNotFound err) {
			throw ProductNotFound(err.getMessage());
		}
		return result;
	}

	public String searchProductByPriceRange(float start, float end)
		throws ProductNotFound
	{
		Product[] result;
		try {
			ArrayList<Product> products = _store.searchByPriceRange(start, end);
			result = products.toArray(new Product[products.size()])
		} catch (ProductNotFound err) {
			throw ProductNotFound(err.getMessage());
		}
		return result;
	}

	public void addToChart(Product product)
	{
		// TODO
	}

	public void removeFromChart(Product product)
	{
		// TODO
	}
}
