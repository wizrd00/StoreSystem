import core.*;
import java.util.Scanner;

public class Main {

	// ─── ANSI Colors ──────────────────────────────────────────
	static final String RESET = "\033[0m";
	static final String BOLD = "\033[1m";
	static final String CYAN = "\033[1;36m";
	static final String GREEN = "\033[1;32m";
	static final String YELLOW = "\033[1;33m";
	static final String RED = "\033[1;31m";
	static final String BLUE = "\033[1;34m";
	static final String DIM = "\033[2m";

	static Scanner scanner = new Scanner(System.in);
	static StoreSystem system;

	// ─── Entry Point ──────────────────────────────────────────
	public static void main(String[] args) {
		SetupStore setup = new SetupStore();
		system = setup.setup();
		printBanner();
		mainLoop();
	}

	// ─── Main Loop ────────────────────────────────────────────
	static void mainLoop() {
		while (true) {
			printMainMenu();
			String choice = prompt("Select");
			switch (choice) {
				case "1":
					menuSearch();
					break;
				case "2":
					menuCart();
					break;
				case "3":
					menuReports();
					break;
				case "4":
					menuCheckout();
					break;
				case "0":
					exitProgram();
					return;
				default:
					printError("Invalid option. Please try again.");
			}
		}
	}

	// ─── Search Menu ──────────────────────────────────────────
	static void menuSearch() {
		printHeader("SEARCH PRODUCTS");
		System.out.println(CYAN + "  [1]" + RESET + " Search by Name");
		System.out.println(CYAN + "  [2]" + RESET + " Search by Price Range");
		System.out.println(CYAN + "  [0]" + RESET + " Back");
		printDivider();
		String choice = prompt("Select");
		switch (choice) {
			case "1":
				searchByName();
				break;
			case "2":
				searchByPriceRange();
				break;
			case "0":
				break;
			default:
				printError("Invalid option.");
		}
	}

	static void searchByName() {
		printHeader("SEARCH BY NAME");
		String name = prompt("Enter product name");
		try {
			Product[] results = system.searchProductByName(name.toLowerCase());
			printSuccess("Found " + results.length + " result(s) for \"" + name + "\"");
			printDivider();
			for (Product p : results)
				printProduct(p);
			printDivider();
			offerAddToCart(results);
		} catch (ProductNotFound e) {
			printError("No products found with name \"" + name + "\"");
		}
	}

	static void searchByPriceRange() {
		printHeader("SEARCH BY PRICE RANGE");
		try {
			float min = parseFloat(prompt("Min price"));
			float max = parseFloat(prompt("Max price"));
			if (min > max) {
				printError("Min price cannot be greater than max price.");
				return;
			}
			Product[] results = system.searchProductByPriceRange(min, max);
			printSuccess("Found " + results.length + " result(s) in range [" + min + " - " + max + "]");
			printDivider();
			for (Product p : results)
				printProduct(p);
			printDivider();
			offerAddToCart(results);
		} catch (ProductNotFound e) {
			printError("No products found in this price range.");
		} catch (NumberFormatException e) {
			printError("Invalid number format. Please enter a valid price.");
		}
	}

	static void offerAddToCart(Product[] results) {
		System.out.println(DIM + "  Enter product ID to add to cart, or press Enter to skip:" + RESET);
		String input = prompt("Product ID (or Enter to skip)");
		if (input.isEmpty())
			return;
		try {
			long id = Long.parseLong(input);
			system.addToChart(id);
			printSuccess("Product added to cart!");
		} catch (ProductNotFound e) {
			printError("Product with ID " + input + " not found.");
		} catch (NumberFormatException e) {
			printError("Invalid ID format.");
		}
	}

	// ─── Cart Menu ────────────────────────────────────────────
	static void menuCart() {
		while (true) {
			printHeader("SHOPPING CART");
			Product[] cart = system.listChart();
			if (cart.length == 0) {
				System.out.println(DIM + "  Your cart is empty." + RESET);
			} else {
				for (Product p : cart)
					printProduct(p);
				printDivider();
				System.out.printf("  %sTotal:%s %.2f%n", BOLD, RESET, system.getChartTotalPrice());
			}
			printDivider();
			System.out.println(CYAN + "  [1]" + RESET + " Remove item from cart");
			System.out.println(CYAN + "  [2]" + RESET + " Clear cart");
			System.out.println(CYAN + "  [0]" + RESET + " Back");
			printDivider();
			String choice = prompt("Select");
			switch (choice) {
				case "1":
					removeFromCart();
					break;
				case "2":
					clearCart();
					break;
				case "0":
					return;
				default:
					printError("Invalid option.");
			}
		}
	}

	static void removeFromCart() {
		String input = prompt("Enter product ID to remove");
		try {
			long id = Long.parseLong(input);
			system.removeFromChart(id);
			printSuccess("Product removed from cart.");
		} catch (ProductNotFound e) {
			printError("Product with ID " + input + " not found in cart.");
		} catch (NumberFormatException e) {
			printError("Invalid ID format.");
		}
	}

	static void clearCart() {
		System.out.print(YELLOW + "  Are you sure you want to clear the cart? (y/n): " + RESET);
		String confirm = scanner.nextLine().trim();
		if (confirm.equalsIgnoreCase("y")) {
			system.clearChart();
			printSuccess("Cart cleared.");
		} else {
			System.out.println(DIM + "  Cancelled." + RESET);
		}
	}

	// ─── Reports Menu ─────────────────────────────────────────
	static void menuReports() {
		printHeader("REPORTS");
		System.out.println(CYAN + "  [1]" + RESET + " Report by Stock Count");
		System.out.println(CYAN + "  [2]" + RESET + " Report by Manufacture Date");
		System.out.println(CYAN + "  [3]" + RESET + " Report by Expiration Date");
		System.out.println(CYAN + "  [4]" + RESET + " Report by Discount");
		System.out.println(CYAN + "  [0]" + RESET + " Back");
		printDivider();
		String choice = prompt("Select");
		String report = "";
		switch (choice) {
			case "1":
				report = system.reportBasedOnCount();
				break;
			case "2":
				report = system.reportBasedOnManufactureDate();
				break;
			case "3":
				report = system.reportBasedOnExpirationDate();
				break;
			case "4":
				report = system.reportBasedOnDiscount();
				break;
			case "0":
				return;
			default:
				printError("Invalid option.");
				return;
		}
		printHeader("REPORT");
		if (report == null || report.isEmpty())
			System.out.println(DIM + "  No data available for this report." + RESET);
		else
			System.out.println(report);
		printDivider();
		prompt("Press Enter to continue");
	}

	// ─── Checkout ─────────────────────────────────────────────
	static void menuCheckout() {
		printHeader("CHECKOUT");
		Product[] cart = system.listChart();
		if (cart.length == 0) {
			printError("Your cart is empty. Add products before checking out.");
			return;
		}
		System.out.println(BOLD + "  Order Summary:" + RESET);
		printDivider();
		for (Product p : cart)
			printProduct(p);
		printDivider();
		System.out.printf("  %sTotal:%s %.2f%n", BOLD, RESET, system.getChartTotalPrice());
		printDivider();
		System.out.print(YELLOW + "  Confirm order? (y/n): " + RESET);
		String confirm = scanner.nextLine().trim();
		if (!confirm.equalsIgnoreCase("y")) {
			System.out.println(DIM + "  Order cancelled." + RESET);
			return;
		}
		try {
			Invoice invoice = system.submitChartOrder();
			printHeader("ORDER CONFIRMED");
			System.out.println(GREEN + "  ✔  Your order was placed successfully!" + RESET);
			printDivider();
			System.out.printf("  %sAmount Paid:%s %.2f%n", BOLD, RESET, invoice.getTotalPrice());
			printDivider();
			prompt("Press Enter to continue");
		} catch (ProductNotFound e) {
			printError("Order failed — product not found: " + e.getMessage());
		} catch (ProductNotAvailable e) {
			printError("Order failed — product not available: " + e.getMessage());
		} catch (DatabaseSyncFailed e) {
			printError("Order failed — could not save to database: " + e.getMessage());
		}
	}

	// ─── Exit ─────────────────────────────────────────────────
	static void exitProgram() {
		printDivider();
		System.out.println(CYAN + "  Goodbye! Thank you for using StoreSystem." + RESET);
		printDivider();
		scanner.close();
		System.exit(0);
	}

	// ─── UI Helpers ───────────────────────────────────────────
	static void printBanner() {
		System.out.println();
		System.out.println(CYAN + "  ╔══════════════════════════════════╗" + RESET);
		System.out.println(CYAN + "  ║       STORE SYSTEM  v1.0         ║" + RESET);
		System.out.println(CYAN + "  ╚══════════════════════════════════╝" + RESET);
		System.out.println();
	}

	static void printMainMenu() {
		System.out.println();
		System.out.println(BOLD + "  MAIN MENU" + RESET);
		printDivider();
		System.out.println(CYAN + "  [1]" + RESET + " Search Products");
		System.out.println(CYAN + "  [2]" + RESET + " Shopping Cart");
		System.out.println(CYAN + "  [3]" + RESET + " Reports");
		System.out.println(CYAN + "  [4]" + RESET + " Checkout");
		System.out.println(CYAN + "  [0]" + RESET + " Exit");
		printDivider();
	}

	static void printHeader(String title) {
		System.out.println();
		System.out.println(BOLD + BLUE + "  ── " + title + " ──" + RESET);
		printDivider();
	}

	static void printDivider() {
		System.out.println(DIM + "  ──────────────────────────────────" + RESET);
	}

	static void printProduct(Product p) {
		System.out.printf("  %s[ID:%d]%s %s%-20s%s %s%.2f%s",
				DIM, p.id, RESET,
				BOLD, p.name, RESET,
				GREEN, p.price, RESET);
		if (p.getDiscount() > 0)
			System.out.printf("  %s-%d%%%s", YELLOW, p.getDiscount(), RESET);
		System.out.printf("  %s(%s)%s", DIM, p.unit, RESET);
		if (p.getManufacturerName() != null)
			System.out.printf("  %sby %s%s", DIM, p.getManufacturerName(), RESET);
		System.out.println();
		if (p.getExpirationDate() != null)
			System.out.printf("  %s     exp: %s%s%n", DIM, p.getExpirationDate(), RESET);
	}

	static void printSuccess(String msg) {
		System.out.println(GREEN + "  ✔  " + msg + RESET);
	}

	static void printError(String msg) {
		System.out.println(RED + "  ✘  " + msg + RESET);
	}

	static String prompt(String label) {
		System.out.print(BOLD + "  > " + label + ": " + RESET);
		return scanner.nextLine().trim();
	}

	static float parseFloat(String input) throws NumberFormatException {
		return Float.parseFloat(input);
	}
}