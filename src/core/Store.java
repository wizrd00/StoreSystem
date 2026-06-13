package core;

import java.util.ArrayList;
import java.util.HashMap;

public class Store {
	private HashMap<String, ArrayList<Product>> _shelf = new HashMap<>();
	private ArrayList<Product> _pending_shelf = new ArrayList<>();
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
					throw new ProductNotAvailable(String.format("The product with name %s and id %d is not available", list.get(i).name, id));
				}
			}
		}
		throw new ProductNotFound(String.format("The product with id %d not found", id));
	}

	public void undoActions()
	{
		for (int i = 0; i < _pending_shelf.size(); i++)
			addProduct(_pending_shelf.get(i));
		_pending_shelf.clear();
	}

	public void finalizeActions()
	{
		_pending_shelf.clear();
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
				if (Tools.isInRange(product.price, start, end))
					result.add(product);
			}
		}
		if (result.size() == 0)
			throw new ProductNotFound(String.format("Product with price in range %f to %f not found", start, end));
		else
			return result;
	}

	public Product searchById(long id)
		throws ProductNotFound
	{
		for (String key : _shelf.keySet()) {
			for (int i = 0; i < _shelf.get(key).size(); i++) {
				if (_shelf.get(key).get(i).id == id)
					return _shelf.get(key).get(i);
			}
		}
		throw new ProductNotFound(String.format("Product with id %d not found", id));
	}

	public String reportBasedOnCount()
	{
		int total_count = getShelf().size();
		String report = "";
		String format = "[%-3d %s]  %s %s\n\n";
		for (String key : _shelf.keySet()) {
			ArrayList<Product> item = _shelf.get(key);
			report += String.format(format, item.size(), (item.size() > 1) ? "products" : "product ", Tools.getCountBar(item.size(), total_count, 64), key);
		}
		return report;
	}

	public String reportBasedOnManufactureDate()
	{
		ArrayList<Product> products = getShelf();
		HashMap<String, ArrayList<Product>> list = new HashMap<>();
		String report = "";
		String format = "[%-3d %s]  %s %s\n\n";
		for (int i = 0; i < products.size(); i++) {
			Product item = products.get(i);
			if (!list.containsKey(item.getManufactureDate()))
				list.put(item.getManufactureDate(), new ArrayList<>());
			list.get(item.getManufactureDate()).add(item);
		}
		for (String key : list.keySet()) {
			ArrayList<Product> item = list.get(key);
			report += String.format(format, item.size(), (item.size() > 1) ? "products" : "product ", Tools.getCountBar(item.size(), products.size(), 64), key);
		}
		return report;
	}

	public String reportBasedOnExpirationDate()
	{
		ArrayList<Product> products = getShelf();
		HashMap<String, ArrayList<Product>> list = new HashMap<>();
		String report = "";
		String format = "[%-3d %s]  %s %s\n\n";
		for (int i = 0; i < products.size(); i++) {
			Product item = products.get(i);
			if (!list.containsKey(item.getExpirationDate()))
				list.put(item.getExpirationDate(), new ArrayList<>());
			list.get(item.getExpirationDate()).add(item);
		}
		for (String key : list.keySet()) {
			ArrayList<Product> item = list.get(key);
			report += String.format(format, item.size(), (item.size() > 1) ? "products" : "product ", Tools.getCountBar(item.size(), products.size(), 64), key);
		}
		return report;
	}

	public String reportBasedOnDiscount()
	{
		ArrayList<Product> products = getShelf();
		String report = "";
		String format = "[%-3d] %-3d%% DISCOUNT for Product %s\n";
		for (int i = 0; i < products.size(); i++) {
			Product item = products.get(i);
			report += String.format(format, i, item.getDiscount(), item.getSpecification());
		}
		return report;
	}
}
