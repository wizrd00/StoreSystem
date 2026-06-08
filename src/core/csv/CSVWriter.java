package core.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class CSVWriter {
	public String path;
	public boolean is_serialized;
	public ArrayList<HashMap<String, String>> content;
	public String[] keys;
	public String saperator = ",";
	private FileWriter _file;
	private BufferedWriter _writer;

	public CSVWriter(String path, ArrayList<HashMap<String, String>> content, String[] keys)
		throws CSVFileNotFound
	{
		this.path = path;
		this.content = content;
		this.keys = keys;
		try {
			_file = new FileWriter(path);
		} catch (IOException err) {
			throw new CSVFileNotFound(err.getMessage());
		}
		_writer = new BufferedWriter(_file);
		is_serialized = false;
	}

	public void serializeCSVFile()
		throws CSVFileAlreadySerialized, CSVFileWriteFailed, CSVFileKeysNotMatch
	{
		if (is_serialized)
			throw new CSVFileAlreadySerialized();
		String keys_as_line = String.join(saperator, keys);
		try {
			_writer.write(keys_as_line, 0, keys_as_line.length());
			_writer.newLine();
			_writer.flush();
		} catch (IOException err) {
			throw new CSVFileWriteFailed(err.getMessage());
		}
		for (int i = 0; i < content.size(); i++) {
			HashMap<String, String> item = content.get(i);
			for (int j = 0; j < keys.length; j++)
				if (!item.containsKey(keys[j]))
					throw new CSVFileKeysNotMatch(String.format("given key %s don't match with hashmap keys", keys[j]));
				// make sure there is not invalid key before writing into file which is impossible to recover
			for (int j = 0; j < keys.length; j++) {
				try {
					_writer.write(item.get(keys[j]), 0, item.get(keys[j]).length());
					if (j + 1 != keys.length)
						_writer.write(saperator, 0, saperator.length());
					else
						_writer.newLine();
				} catch (IOException err) {
					throw new CSVFileWriteFailed(err.getMessage());
				}
			}
			try {
				_writer.flush();
			} catch (IOException err) {
				throw new CSVFileWriteFailed(err.getMessage());
			}
		}
		try {
			_writer.close();
		} catch (IOException err) {
			throw new CSVFileWriteFailed(err.getMessage());
		}
		is_serialized = true;
	}


}
