package core;

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
