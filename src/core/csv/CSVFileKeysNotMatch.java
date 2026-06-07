package core.csv;

public class CSVFileKeysNotMatch extends Exception {
	public CSVFileKeysNotMatch(String message)
	{
		super(message);
	}

	public CSVFileKeysNotMatch()
	{
		super();
	}
}
