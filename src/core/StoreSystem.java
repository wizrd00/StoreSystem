package core;

import java.util.ArrayList;

public class StoreSystem {
	private ShoppingChart _chart = new ShoppingChart();
	private Store _store;
	private Uploader _uploader;

	public StoreSystem(Store store, Uploader uploader)
	{
		this._store = store;
		this._uploader = uploader;
	}

	public Product[] searchProductByName(String name)
		throws ProductNotFound
	{
		Product[] result;
		try {
			ArrayList<Product> products = _store.searchByName(name);
			result = products.toArray(new Product[products.size()]);
		} catch (ProductNotFound err) {
			throw new ProductNotFound(err.getMessage());
		}
		return result;
	}

	public Product[] searchProductByPriceRange(float start, float end)
		throws ProductNotFound
	{
		Product[] result;
		try {
			ArrayList<Product> products = _store.searchByPriceRange(start, end);
			result = products.toArray(new Product[products.size()]);
		} catch (ProductNotFound err) {
			throw new ProductNotFound(err.getMessage());
		}
		return result;
	}

	public void addToChart(long id)
		throws ProductNotFound
	{
		Product product;
		try {
			product = _store.searchById(id);
		} catch (ProductNotFound err) {
			throw new ProductNotFound(err.getMessage());
		}
		_chart.addNewProduct(product);
	}

	public void removeFromChart(long id)
		throws ProductNotFound
	{
		try {
			_chart.removeSpecificProduct(id);
		} catch (ProductNotFound err) {
			throw new ProductNotFound(err.getMessage());
		}
	}

	public float getChartTotalPrice()
	{
		return _chart.getChartTotalPrice();
	}

	public void clearChart()
	{
		_chart.removeAllProducts();
	}

	public Invoice submitChartOrder()
		throws ProductNotFound, ProductNotAvailable, DatabaseSyncFailed
	{
		Product[] products = _chart.getChartList();
		Invoice invoice = new Invoice();
		_store.finalizeActions();
		for (int i = 0; i < products.length; i++)
			try {
				_store.buyProduct(products[i].id);
			} catch (ProductNotFound err0) {
				throw new ProductNotFound(String.format("Failed to submit the order : %s", err0.getMessage()));
			} catch (ProductNotAvailable err1) {
				throw new ProductNotAvailable(String.format("Failed to submit the order : %s", err1.getMessage()));
			}
		try {
			syncDatabase();
		} catch (DatabaseSyncFailed err) {
			_store.undoActions();
			throw new DatabaseSyncFailed("Database Syncing failed");
		}
		_store.finalizeActions();
		invoice.addProducts(products);
		clearChart();
		return invoice;
	}

	public void ReturnProduct(Product product)
		throws DatabaseSyncFailed
	{
		_store.addProduct(product);
		try {
			syncDatabase();
		} catch (DatabaseSyncFailed err) {
			throw new DatabaseSyncFailed("Database Syncing failed");
		}
	}

	public void syncDatabase()
		throws DatabaseSyncFailed
	{
		try {
			_uploader.upload(_store.getShelf());
		} catch (UploadFailed err) {
			throw new DatabaseSyncFailed(err.getMessage());
		}
	}

	public String reportBasedOnCount()
	{
		return _store.reportBasedOnCount();
	}

	public String reportBasedOnManufactureDate()
	{
		return _store.reportBasedOnManufactureDate();
	}

	public String reportBasedOnExpirationDate()
	{
		return _store.reportBasedOnExpirationDate();
	}

	public String reportBasedOnDiscount()
	{
		return _store.reportBasedOnDiscount();
	}
}
