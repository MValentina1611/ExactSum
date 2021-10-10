package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ImportData {

	private final String TEST_FILE = "data/testFile.txt";
	
	public String importData() throws IOException
	{
		BufferedReader brf = new BufferedReader(new FileReader(TEST_FILE) );
		String line = brf.readLine();
		String inputInfo = "";
		
		while( line != null )
		{								
			inputInfo += line;
			inputInfo += " ";
			//System.out.println(inputInfo);
			line = brf.readLine();
		}
		brf.close();
		return inputInfo;
	}	
	
}
