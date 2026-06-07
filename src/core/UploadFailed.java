package core;

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
