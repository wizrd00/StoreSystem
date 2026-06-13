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

	public static String getCountBar(int count, int total, int length)
	{
		int block_count = (int) ((float) count / (float) total * (float) length);
		return ("█".repeat(block_count) + "░".repeat(length - block_count));
	}
}
