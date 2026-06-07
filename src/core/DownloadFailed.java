package core;

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
