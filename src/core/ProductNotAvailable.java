package core;

public class ProductNotAvailable extends Exception {
	public ProductNotAvailable(String message)
	{
		super(message);
	}

	public ProductNotAvailable()
	{
		super();
	}
}
