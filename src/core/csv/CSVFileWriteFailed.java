package core.csv;

public class CSVFileWriteFailed extends Exception {
	public CSVFileWriteFailed(String message)
	{
		super(message);
	}

	public CSVFileWriteFailed()
	{
		super();
	}
}
