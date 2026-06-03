package core;

public class ProductNotFound extends Exception {
	public ProductNotFound(String message)
	{
		super(message);
	}
}

public class ProductNotAvailable extends Exception {
	public ProductNotAvailable(String message)
	{
		super(message);
	}
}
