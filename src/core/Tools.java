package core;

public class Tools {
	public static String unitToString(Unit unit)
	{
		String value;
		switch (unit) {
		case PIECE :
			value = "pcs";
			break;
		case GRAM :
			value = "g";
			break;
		case KILOGRAM :
			value = "Kg";
			break;
		case METER :
			value = "m";
			break;
		case LITER :
			value = "L";
			break;
		default :
			value = "";
			break;
		}
		return value;
	}

	public static String makeProductSpecification(Product product)
	{
		// TODO
	}

	public static Product createNewProduct()
	{
		// TODO
	}

	public static boolean isInRange(int value, int start, int end)
	{
		return ((value >= start) && (value <= end));
	}

	public static boolean isInRange(float value, float start, float end)
	{
		return ((value >= start) && (value <= end));
	}
}
