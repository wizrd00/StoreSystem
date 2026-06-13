package core;

public class Invoice {
	private Product[] _products;

	public void addProducts(Product[] products)
	{
		_products = products;
	}

	public float getTotalPrice()
	{
		float price = 0.0f;
		for (int i = 0; i < _products.length; i++)
			price += _products[i].price;
		return price;
	}

	public String getPrintable()
	{
		String result = "";
		String format = "[%d] Product %s with real price %f$ and final price %f$\n";
		for (int i = 0; i < _products.length; i++) {
			Product item = _products[i];
			result += String.format(format, i, item.name, item.price, (item.price - (item.price * (float) item.getDiscount() / 100.0f)));
		}
		return result;
	}
}
