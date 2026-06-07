package core.csv;

public class CSVFileAlreadySerialized extends Exception {
	public CSVFileAlreadySerialized(String message)
	{
		super(message);
	}

	public CSVFileAlreadySerialized()
	{
		super();
	}
}
