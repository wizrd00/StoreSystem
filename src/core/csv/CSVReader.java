package core.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CSVReader {
	public String path;
	public boolean is_parsed;
	private FileReader _file;
	private BufferedReader _reader;
	private ArrayList<HashMap<String, String>> _content = new ArrayList<>();

	public CSVReader(String path)
		throws CSVFileNotFound
	{
		this.path = path;
		this.is_parsed = false;
		try {
			_file = new FileReader(path);
		} catch (FileNotFoundException err) {
			throw new CSVFileNotFound(err.getMessage());
		}
		_reader = new BufferedReader(_file);
	}

	public void parseCSVFile()
		throws CSVFileAlreadyParsed, CSVFileReadFailed, CSVFileInvalidFormat
	{
		if (is_parsed)
			throw new CSVFileAlreadyParsed();
		String line;
		String[] keys;
		int index = 0;
		try {
			line = _reader.readLine();
		} catch (IOException err) {
			throw new CSVFileReadFailed(err.getMessage());
		}
		if (line == null)
			throw new CSVFileReadFailed("the CSV file doesn't contain keys");
		keys = line.split(",");
		while (true) {
			String[] values;
			try {line = _reader.readLine();} catch (IOException err) {throw new CSVFileReadFailed(err.getMessage());}
			if (line == null) break;
			values = line.split(",");
			if (values.length != keys.length)
				throw new CSVFileInvalidFormat(String.format("invalid csv format at line %d : \"%s\"", index + 1, line));
			_content.add(new HashMap<>());
			for (int i = 0; i < keys.length; i++) {
				_content.get(index).put(keys[i], values[i]);
			}
			index++;
		}
		try {
			_reader.close();
		} catch (IOException err) {
			throw new CSVFileReadFailed(err.getMessage());
		}
		is_parsed = true;
	}

	public int getContentLength()
	{
		return _content.size();
	}

	public ArrayList<HashMap<String, String>> getContent()
	{
		return _content;
	}
}
