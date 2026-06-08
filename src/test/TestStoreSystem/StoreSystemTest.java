package test.TestStoreSystem;

import core.*;

public class StoreSystemTest {
	public static void main(String[] args) {
		SetupStore setup = new SetupStore();
		StoreSystem system = setup.setup();
		try {
			Product[] list = system.searchProductByName("milk");
			for (int i = 0; i < list.length; i++)
				assert list[i].name.equals("milk");

			list = system.searchProductByPriceRange(0.0f, 5.0f);
			assert list.length == 1;
			assert list[0].name.equals("ice cream");
			assert list[0].price == 4.00f;
			assert list[0].id == 2;

			list = system.searchProductByPriceRange(15.00f, 25.00f);
			assert list.length == 2;
			assert list[0].name.equals("cheeze");
			assert list[1].name.equals("cheeze");

			system.addToChart(3);
			list = system.listChart();
			assert list.length == 1;
			assert list[0].name.equals("milk");
			assert list[0].id == 3;
			assert list[0].price == 10.00f;

			system.removeFromChart(3);
			list = system.listChart();
			assert list.length == 0;

			system.addToChart(0);
			system.addToChart(2);
			system.addToChart(3);
			list = system.listChart();
			assert list.length == 3;
			assert system.getChartTotalPrice() == 25.73f;

			Invoice invoice = system.submitChartOrder();
			list = system.listChart();
			assert list.length == 0;
			list = system.searchProductByName("milk");
			assert list.length == 0;
			list = system.searchProductByName("ice cream");
			assert list.length == 0;

			Product new_product = new Product("chips", "pcs", 4.67f, 0);
			new_product.setAvailable();
			new_product.setManufacturerName("chetoz");
			new_product.setColor("none");
			new_product.setManufactureDate("1404/02/02");
			new_product.setExpirationDate("1405/02/02");
			new_product.setWeight(10.45f);
			new_product.setVolume(0.0f);
			new_product.setDiscount(0);
			system.returnProduct(new_product);
			list = system.searchProductByName("chips");
			assert list.length == 1;
		} catch (Exception err) {
			System.out.println("ERROR");
			System.out.println(err.getMessage());
			System.exit(1);
		}
	}
}
