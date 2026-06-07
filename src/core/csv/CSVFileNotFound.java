package core.csv;

public class CSVFileNotFound extends Exception {
	public CSVFileNotFound(String message)
	{
		super(message);
	}

	public CSVFileNotFound()
	{
		super();
	}
}
