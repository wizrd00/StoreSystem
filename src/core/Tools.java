package core.Tools;

import core.Types;

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
			break;
		}
		return value;
	}

	public static String makeProductSpecification(Product product)
	{
		//TODO
	}
}
