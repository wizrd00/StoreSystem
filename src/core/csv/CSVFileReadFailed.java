package core.csv;

public class CSVFileReadFailed extends Exception {
	public CSVFileReadFailed(String message)
	{
		super(message);
	}

	public CSVFileReadFailed()
	{
		super();
	}
}
