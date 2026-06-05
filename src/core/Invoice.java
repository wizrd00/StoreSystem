package core;

public class Invoice {
	private Product[] _products;

	public void addProducts(Product[] products)
	{
		_products = products;
	}

	public float getTotalPrice()
	{
		float price = 0.0;
		for (int i = 0; i < _products.lenght; i++)
			price += _products[i].price;
		return price;
	}

	public String getPrintable()
	{
		String result;
		// TODO
	}
}
