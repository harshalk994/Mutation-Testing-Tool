//	This class is created to first temp file generated line by line and process it to remove comments if present on any line

package com.hsk.tempfileprocessing;
import com.hsk.*;
import java.io.File;    
import java.io.FileReader;    
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;    

public class Cleaner{    

	private static String mPath;

	//----------Method to get the mutant folder path-----------------
	public void getPath(String mutantFilePath) {
		mPath = mutantFilePath;
	}

	//----------Method to read temp file line by line-----------------
	public String readFile() {    
		String tempFileName = mPath+"/OriginalTempCopy.java";
		File file = new File(tempFileName);    

		char[] buffer = null;    

		try {    
			BufferedReader bufferedReader = new BufferedReader( new FileReader(file));    

			buffer = new char[(int)file.length()];    

			int i = 0;    
			int c = bufferedReader.read();    

			while (c != -1) {    
				buffer[i++] = (char)c;    
				c = bufferedReader.read();    
			}

			bufferedReader.close();

		} catch (IOException e) {    
			e.printStackTrace();    
		} 

		return new String(buffer);    
	}    

}    