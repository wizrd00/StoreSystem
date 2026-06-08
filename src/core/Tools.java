package core;

public class Tools {
	public static boolean isInRange(int value, int start, int end)
	{
		return ((value >= start) && (value <= end));
	}

	public static boolean isInRange(float value, float start, float end)
	{
		return ((value >= start) && (value <= end));
	}
}
