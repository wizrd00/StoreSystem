package core;

public class ProductNotFound extends Exception {
	public ProductNotFound(String message)
	{
		super(message);
	}

	public ProductNotFound()
	{
		super();
	}
}

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

public class DownloadFailed extends Exception {
	public DownloadFailed(String message)
	{
		super(message);
	}

	public DownloadFailed()
	{
		super();
	}
}

public class UploadFailed extends Exception {
	public UploadFailed(String message)
	{
		super(message);
	}

	public UploadFailed()
	{
		super();
	}
}

public class DatabaseSyncFailed extends Exception {
	public DatabaseSyncFailed(String message)
	{
		super(message);
	}

	public DatabaseSyncFailed()
	{
		super();
	}
}
