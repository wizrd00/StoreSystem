package core.csv;

public class CSVFileInvalidFormat extends Exception {
	public CSVFileInvalidFormat(String message)
	{
		super(message);
	}

	public CSVFileInvalidFormat()
	{
		super();
	}
}
