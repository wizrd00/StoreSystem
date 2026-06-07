package core.csv;

public class CSVFileAlreadyParsed extends Exception {
	public CSVFileAlreadyParsed(String message)
	{
		super(message);
	}

	public CSVFileAlreadyParsed()
	{
		super();
	}
}
