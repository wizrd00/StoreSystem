package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
	private HashMap<String, ArrayList<Product>> _shelf = new HashMap<>();
	// I choose this data architecture because for example a Product with name "Milk" can have different
	// manufacturer name, color or event expiration date, so I think HashMap is clever choice but I know it can be memory-hungry
	// However I'm not a java expert and this language it's not even close to be as brilliant as C

	public void addProduct(Product product)
	{
		if (_shelf.containsKey(product.name)) {
			_shelf.get(product.name).add(product);
			return;
		}
		ArrayList<Product> new_product = new ArrayList<>();
		new_product.add(product);
		_shelf.put(product.name, new_product);
		return;
	}

	public ArrayList<Product> searchByName(String name)
		throws ProductNotFound
	{
		if (!_shelf.containsKey(name))
			throw new ProductNotFound(String.format("Product with %s not found", name));
		return _shelf.get(name);
	}

	public ArrayList<Product> searchByPriceRange(float start, float end)
		throws ProductNotFound
	{
		ArrayList<Product> result = new ArrayList<>();
		for (String key : _shelf.keySet()) {
			for (int i = 0; i < _shelf.get(key).size(); i++) {
				Product product = _shelf.get(key).get(i);
				if (Tools.isInRange(product.price, start, end)
					result.add(product);
			}
		}
		if (result.size() == 0)
			throw new ProductNotFound(String.format("Product with price in range %f to %f not found", start, end));
		else
			return result;
	}
}
