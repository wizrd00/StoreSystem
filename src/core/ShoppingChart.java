package core;

import java.util.ArrayList;

public class ShoppingChart {
	private ArrayList<Product> _products = new ArrayList<>();

	public Product[] getChartList()
	{
		return _product.toArray(new Product[_product.size()]);
	}

	public void addNewProduct(Product product)
	{
		_products.add(product);
		return;
	}

	public void removeSpecificProduct(long id)
		throws ProductNotFound
	{
		for (int i = 0; i < _products.size(); i++)
			if (_products.get(i).id == id)
				_products.remove(i);
			else
				throw new ProductNotFound(String.format("Product with id %ld not found", id));
	}

	public float getChartTotalPrice()
	{
		float price = 0.0;
		for (int i = 0; i < _products.size(); i++)
			price += _products.get(i).price;
		return price;
	}

	public void removeAllProducts()
	{
		_products.clear();
	}
}
