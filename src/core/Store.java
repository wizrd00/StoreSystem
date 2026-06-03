package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
	private HashMap<String, ArrayList<Product>> _shelf = new HashMap<>();
	// I choose this data architecture because for example a Product with name "Milk" can have different
	// manufacturer name, color or event expiration date, so I think HashMap is clever choice but I know it can be memory-hungry
	// However I'm not a java expert and this language it's not even close to be as brilliant as C

	public ArrayList<Product> getShelf()
	{
		ArrayList<Product> products = new ArrayList<>();
		for (String key : _shelf.keySet())
			for (int i = 0; i < _shelf.get(key).size(); i++)
				products.add(_shelf.get(key).get(i));
		return products;
	}

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

	public void buyProduct(long id)
		throws ProductNotFound, ProductNotAvailable
	{
		for (String key : _shelf.keySet()) {
			ArrayList<Product> list = _shelf.get(key);
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).id != id)
					continue;
				if (list.get(i).getAvailable()) {
					list.remove(i);
					return;
				} else {
					throw new ProductNotAvailable(String.format("The product with name %s and id %l is not available", name, id));
				}
			}
		}
		throw new ProductNotFound("The product with name %s and id %l not found", name, id);
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
